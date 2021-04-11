package br.com.amz.replay.offer.usecase

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
) : OfferInputPort {
    override suspend fun save(offer: Offer) = coroutineScope {
        logger.info("Saving offer: $offer")

        offerDataAccessPort.save(offer).also {
            logger.info("Offer saved: $it")
        }
    }

    override suspend fun findAll(): List<Offer> = coroutineScope {
        offerDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
