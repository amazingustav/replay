package br.com.amz.replay.offer.ports.input

import br.com.amz.replay.offer.model.Offer

interface OfferInputPort {
    suspend fun save(offer: Offer): Offer
    suspend fun findAll(): List<Offer>
}
