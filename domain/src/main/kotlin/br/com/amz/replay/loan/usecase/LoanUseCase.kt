package br.com.amz.replay.loan.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.model.OfferSubmissionResult
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import java.util.UUID
import javax.inject.Singleton

@Singleton
class LoanUseCase(
    private val loanDataAccessPort: LoanDataAccessPort,
    private val offerDataAccessPort: OfferDataAccessPort
) : LoanInputPort {

    override suspend fun findAll(): List<Loan> = loanDataAccessPort.findAll()

    override suspend fun findByUser(userId: UUID): List<Loan> = loanDataAccessPort.findByUser(userId)

    override suspend fun submitOffer(offer: Offer, loanId: UUID): OfferSubmissionResult {
        val offerSubmitted = offerDataAccessPort.save(offer)

        val loan = loanDataAccessPort.findById(loanId)
            ?: throw ResourceNotFoundException("Loan not found while submit offer")

        loan.copy(offer = offerSubmitted).also {
            loanDataAccessPort.update(it)
        }

        return OfferSubmissionResult(
            title = SUBMISSION_SUCCESS_TITLE,
            message = SUBMISSION_SUCCESS_MESSAGE
        )
    }

    companion object {
        private const val SUBMISSION_SUCCESS_TITLE = "Congratulations"
        private const val SUBMISSION_SUCCESS_MESSAGE = "You're about to lower your monthly auto loan payment! " +
                "A loan officer will reach out to you shortly to discuss the final steps and confirm the loan offer."
    }
}
