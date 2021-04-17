package br.com.amz.replay.offer.usecase

import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import kotlinx.coroutines.coroutineScope
import javax.inject.Singleton

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
) : OfferInputPort {

    override suspend fun findAll() = coroutineScope {
        offerDataAccessPort.findAll()
    }
}
