package br.com.amz.replay.offer.ports.output

import br.com.amz.replay.offer.model.Offer

interface OfferDataAccessPort {
    suspend fun save(offer: Offer): Offer
    suspend fun findAll(): List<Offer>
}
