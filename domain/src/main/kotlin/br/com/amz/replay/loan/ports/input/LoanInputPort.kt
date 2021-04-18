package br.com.amz.replay.loan.ports.input

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.model.OfferSubmissionResult
import br.com.amz.replay.offer.model.Offer
import java.util.UUID

interface LoanInputPort {

    suspend fun findAll(): List<Loan>

    suspend fun findByUser(userId: UUID): List<Loan>

    suspend fun submitOffer(offer: Offer, loanId: UUID): OfferSubmissionResult
}
