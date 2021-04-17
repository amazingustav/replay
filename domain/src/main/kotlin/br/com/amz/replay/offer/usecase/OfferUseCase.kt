package br.com.amz.replay.offer.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
    private val userDataAccessPort: UserDataAccessPort,
) : OfferInputPort {

    override suspend fun save(offer: OfferInput) = coroutineScope {
        val user = userDataAccessPort.findById(offer.userId)
            ?: throw ResourceNotFoundException("User ${offer.userId} not found while saving offer")

        offerDataAccessPort.save(
            Offer(
                annualPercentageRate = offer.annualPercentageRate,
                monthlyPaymentAmount = offer.monthlyPaymentAmount,
                user = user,
                paymentAmount = offer.paymentAmount
            )
        ).also {
            logger.info("Offer saved. Id: ${it.id}")
        }
    }

    override suspend fun findAll() = coroutineScope {
        offerDataAccessPort.findAll()
    }

    override suspend fun findByUser(userId: UUID) = coroutineScope {
        offerDataAccessPort.findByUserId(userId)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
