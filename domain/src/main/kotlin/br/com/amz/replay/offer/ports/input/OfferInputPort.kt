package br.com.amz.replay.offer.ports.input

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.model.ProposalOffer
import java.util.UUID

interface OfferInputPort {

    suspend fun findAll(): List<Offer>

    suspend fun generateProposalOffers(loanId: UUID): List<ProposalOffer>
}
