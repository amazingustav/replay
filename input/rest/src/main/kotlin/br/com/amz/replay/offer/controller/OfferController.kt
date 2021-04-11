package br.com.amz.replay.offer.controller

import br.com.amz.replay.offer.dto.OfferInputDTO
import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.offer.ports.input.OfferInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.coroutineScope

@Controller("/offer")
class OfferController(
    private val offerInputPort: OfferInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body offerInputDTO: OfferInputDTO) = coroutineScope {
        offerInputPort.save(offerInputDTO.toModel()).toDTO()
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        offerInputPort.findAll().map { it.toDTO() }
    }
}
