package br.com.amz.replay.offer.ports.input

import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.offer.model.Offer

interface OfferInputPort {
    suspend fun save(offer: OfferInput): Offer
    suspend fun findAll(): List<Offer>
}
