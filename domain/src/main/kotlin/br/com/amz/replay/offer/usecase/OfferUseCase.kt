package br.com.amz.replay.offer.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
    private val userDataAccessPort: UserDataAccessPort,
) : OfferInputPort {
    override suspend fun save(offer: OfferInput) = coroutineScope {
        logger.info("Saving offer ${offer.id}")

        val user = userDataAccessPort.findById(offer.userId)
            ?: throw ResourceNotFoundException("User not found for id ${offer.userId}")

        offerDataAccessPort.save(
            Offer(
                annualPercentageRate = offer.annualPercentageRate,
                monthlyPaymentAmount = offer.monthlyPaymentAmount,
                user = user,
                paymentAmount = offer.paymentAmount
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
