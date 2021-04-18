package br.com.amz.replay.offer.controller

import br.com.amz.replay.offer.dto.OfferDTO
import br.com.amz.replay.offer.dto.ProposalOfferDTO
import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.offer.ports.input.OfferInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.UUID

@Controller("/offers")
class OfferController(
    private val offerInputPort: OfferInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll(): List<OfferDTO> {
        return offerInputPort.findAll().map { it.toDTO() }
    }

    @Get(value = "/generate/{loanId}", produces = [APPLICATION_JSON])
    suspend fun generateProposalOffers(@PathVariable loanId: UUID): List<ProposalOfferDTO> {
        return offerInputPort.generateProposalOffers(loanId).map { it.toDTO() }
    }
}
