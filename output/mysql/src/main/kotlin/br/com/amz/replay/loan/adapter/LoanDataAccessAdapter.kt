package br.com.amz.replay.loan.adapter

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.loan.repository.LoanRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class LoanDataAccessAdapter(
    private val loanRepository: LoanRepository
) : LoanDataAccessPort {

    override suspend fun update(loan: Loan) {
        loanRepository.updateOffer(offerId = loan.offer.id, loanId = loan.id)
    }

    override suspend fun findById(id: UUID): Loan? {
        return loanRepository.findById(id)
            .awaitSingleOrNull()
            ?.toModel()
    }

    override suspend fun findAll(): List<Loan> {
        return loanRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }

    override suspend fun findByUser(userId: UUID): List<Loan> {
        return loanRepository.findByUserId(userId)
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}
