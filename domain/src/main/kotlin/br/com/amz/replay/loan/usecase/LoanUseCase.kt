package br.com.amz.replay.loan.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.model.LoanInput
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class LoanUseCase(
    private val loanDataAccessPort: LoanDataAccessPort,
    private val userDataAccessPort: UserDataAccessPort,
    private val vehicleDataAccessPort: VehicleDataAccessPort
) : LoanInputPort {
    override suspend fun save(loanInput: LoanInput) = coroutineScope {
        logger.info("Saving loan $loanInput")

        val user = userDataAccessPort.findById(loanInput.userId)
            ?: throw ResourceNotFoundException("User not found for id ${loanInput.userId}")

        val vehicle = vehicleDataAccessPort.findById(loanInput.vehicleId)
            ?: throw ResourceNotFoundException("Vehicle not found for id ${loanInput.vehicleId}")

        val loan = LoanOutput(
            user = user,
            vehicle = vehicle
        )

        loanDataAccessPort.save(loan).also {
            logger.info("Loan saved $it")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
