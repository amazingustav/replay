package br.com.amz.replay.offer.controller

import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.offer.ports.input.OfferInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.coroutineScope

@Controller("/offer")
class OfferController(
    private val offerInputPort: OfferInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        offerInputPort.findAll().map { it.toDTO() }
    }
}
