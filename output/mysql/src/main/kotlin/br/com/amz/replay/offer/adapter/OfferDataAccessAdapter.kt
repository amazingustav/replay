package br.com.amz.replay.offer.adapter

import br.com.amz.replay.offer.dbo.toDBO
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.offer.repository.OfferRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import javax.inject.Singleton

@Singleton
internal class OfferDataAccessAdapter(
    private val offerRepository: OfferRepository
) : OfferDataAccessPort {
    override suspend fun save(offer: Offer) = coroutineScope {
        offerRepository.save(offer.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findAll(): List<Offer> = coroutineScope {
        offerRepository
            .findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}