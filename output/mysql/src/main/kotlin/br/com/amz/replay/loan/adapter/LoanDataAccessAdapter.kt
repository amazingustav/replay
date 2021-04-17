package br.com.amz.replay.loan.adapter

import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.loan.repository.LoanRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.*
import javax.inject.Singleton

@Singleton
internal class LoanDataAccessAdapter(
    private val loanRepository: LoanRepository
) : LoanDataAccessPort {

    override suspend fun findById(id: UUID) = coroutineScope {
        loanRepository.findById(id)
            .awaitSingleOrNull()
            ?.toModel()
    }

    override suspend fun findAll() = coroutineScope {
        loanRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}
