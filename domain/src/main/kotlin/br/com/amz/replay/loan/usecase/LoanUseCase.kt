package br.com.amz.replay.loan.usecase

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import kotlinx.coroutines.coroutineScope
import javax.inject.Singleton

@Singleton
class LoanUseCase(
    private val loanDataAccessPort: LoanDataAccessPort,
) : LoanInputPort {

    override suspend fun findAll(): List<Loan> = coroutineScope {
        loanDataAccessPort.findAll()
    }
}
