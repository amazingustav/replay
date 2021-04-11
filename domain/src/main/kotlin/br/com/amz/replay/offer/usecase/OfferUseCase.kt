package br.com.amz.replay.offer.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
    private val loanDataAccessPort: LoanDataAccessPort,
) : OfferInputPort {
    override suspend fun save(offer: OfferInput) = coroutineScope {
        logger.info("Saving offer ${offer.id}")

        val loan = loanDataAccessPort.findById(offer.loanId)
            ?: throw ResourceNotFoundException("Loan not found for id ${offer.loanId}")

        offerDataAccessPort.save(
            Offer(
                annualPercentageRate = offer.annualPercentageRate,
                monthlyPaymentAmount = offer.monthlyPaymentAmount,
                loan = loan,
                lenderName = offer.lenderName,
                paymentAmount = offer.paymentAmount,
                paidAmount = offer.paidAmount
            )
        ).also {
            logger.info("Offer ${it.id} saved")
        }
    }

    override suspend fun findAll(): List<Offer> = coroutineScope {
        offerDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
