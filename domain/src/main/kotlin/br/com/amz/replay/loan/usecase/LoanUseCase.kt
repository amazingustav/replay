package br.com.amz.replay.loan.usecase

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import java.util.UUID
import javax.inject.Singleton

@Singleton
class LoanUseCase(
    private val loanDataAccessPort: LoanDataAccessPort,
) : LoanInputPort {

    override suspend fun findAll(): List<Loan> = loanDataAccessPort.findAll()

    override suspend fun findByUser(userId: UUID): List<Loan> = loanDataAccessPort.findByUser(userId)
}
