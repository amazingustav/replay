package br.com.amz.replay.offer.controller

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.offer.ports.input.OfferInputPort
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import kotlinx.coroutines.coroutineScope
import java.util.UUID

@Controller("/offers")
class OfferController(
    private val offerInputPort: OfferInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        offerInputPort.findAll().map { it.toDTO() }
    }

    @Get(value = "/generate/{loanId}", produces = [APPLICATION_JSON])
    suspend fun generateProposalOffers(@PathVariable loanId: UUID): MutableHttpResponse<out Any> {
        return try {
            val result = offerInputPort.generateProposalOffers(loanId).map { it.toDTO() }

            HttpResponse.ok(result)
        } catch (ex: ResourceNotFoundException) {
            HttpResponse.notFound(
                mapOf("message" to ex.message)
            )
        } catch (ex: Exception) {
            HttpResponse.serverError(
                mapOf("message" to "Error while generating proposal offers")
            )
        }
    }
}
