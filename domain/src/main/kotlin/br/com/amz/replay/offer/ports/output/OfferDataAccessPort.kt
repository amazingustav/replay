package br.com.amz.replay.offer.ports.output

import br.com.amz.replay.offer.model.Offer
import java.util.UUID

interface OfferDataAccessPort {

    suspend fun save(offer: Offer): Offer

    suspend fun findById(id: UUID): Offer?

    suspend fun findAll(): List<Offer>

    suspend fun findByUserId(userId: UUID): List<Offer>
}
