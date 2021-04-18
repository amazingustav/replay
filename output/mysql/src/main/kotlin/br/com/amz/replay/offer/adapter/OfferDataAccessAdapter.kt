package br.com.amz.replay.offer.adapter

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.offer.repository.OfferRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class OfferDataAccessAdapter(
    private val offerRepository: OfferRepository
) : OfferDataAccessPort {

    override suspend fun findById(id: UUID): Offer? {
        return offerRepository.findById(id)
            .awaitSingleOrNull()
            ?.toModel()
    }

    override suspend fun findAll(): List<Offer> {
        return offerRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}
