package br.com.amz.replay.loan.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.model.LoanInput
import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import java.util.UUID
import javax.inject.Singleton

@Singleton
class LoanUseCase(
    private val loanDataAccessPort: LoanDataAccessPort,
    private val userDataAccessPort: UserDataAccessPort,
    private val vehicleDataAccessPort: VehicleDataAccessPort
) : LoanInputPort {

    override suspend fun save(loanInput: LoanInput) = coroutineScope {
        val user = userDataAccessPort.findById(loanInput.userId)
            ?: throw ResourceNotFoundException("User ${loanInput.userId} not found while creating a loan")

        val vehicle = vehicleDataAccessPort.findById(loanInput.vehicleId)
            ?: throw ResourceNotFoundException("Vehicle ${loanInput.vehicleId} not found while creating a loan")

        val loan = LoanOutput(
            user = user,
            vehicle = vehicle,
            lenderName = loanInput.lenderName,
            paidAmount = loanInput.paidAmount,
            balance = loanInput.balance
        )

        loanDataAccessPort.save(loan).also {
            logger.info("Loan saved. Id: ${it.id}")
        }
    }

    override suspend fun findByUser(userId: UUID) = coroutineScope {
        loanDataAccessPort.findByUserId(userId)
    }

    override suspend fun findAll(): List<LoanOutput> = coroutineScope {
        loanDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
