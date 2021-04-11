package br.com.amz.replay.loan.adapter

import br.com.amz.replay.loan.dbo.toDBO
import br.com.amz.replay.loan.model.LoanInput
import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.loan.repository.LoanRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class LoanDataAccessAdapter(
    private val loanRepository: LoanRepository
) : LoanDataAccessPort {
    override suspend fun save(loanOutput: LoanOutput) = coroutineScope {
        loanRepository.save(loanOutput.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findById(id: UUID) = coroutineScope {
        loanRepository.findById(id)
            .awaitSingleOrNull()
            ?.toModel()
    }
}
