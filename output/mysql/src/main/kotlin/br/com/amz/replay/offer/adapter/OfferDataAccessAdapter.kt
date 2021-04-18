package br.com.amz.replay.offer.adapter

import br.com.amz.replay.offer.dbo.toDBO
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.offer.repository.OfferRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class OfferDataAccessAdapter(
    private val offerRepository: OfferRepository
) : OfferDataAccessPort {

    override suspend fun save(offer: Offer): Offer {
        return offerRepository.save(offer.toDBO())
            .awaitSingle()
            .toModel()
    }

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
