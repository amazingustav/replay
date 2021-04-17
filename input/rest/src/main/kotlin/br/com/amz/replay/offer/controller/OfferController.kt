package br.com.amz.replay.offer.controller

import br.com.amz.replay.offer.dto.OfferInputDTO
import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.offer.ports.input.OfferInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.*
import kotlinx.coroutines.coroutineScope
import java.util.*

@Controller("/offer")
class OfferController(
    private val offerInputPort: OfferInputPort
) {

    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body offerInputDTO: OfferInputDTO) = coroutineScope {
        offerInputPort.save(offerInputDTO.toModel()).toDTO()
    }

    @Get(value = "/userId/{userId}", produces = [APPLICATION_JSON])
    suspend fun findByUser(@PathVariable("userId") userId: UUID) = coroutineScope {
        offerInputPort.findByUser(userId).map { it.toDTO() }
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        offerInputPort.findAll().map { it.toDTO() }
    }
}
