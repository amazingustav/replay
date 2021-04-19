package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.LoanDTO
import br.com.amz.replay.loan.dto.OfferSubmissionDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.model.OfferSubmissionResult
import br.com.amz.replay.loan.ports.input.LoanInputPort
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.util.UUID

@Controller("/loans")
class LoanController(
    private val loanInputPort: LoanInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll(): List<LoanDTO> {
        return loanInputPort.findAll().map { it.toDTO() }
    }

    @Get(value = "/users/{userId}", produces = [APPLICATION_JSON])
    suspend fun findByUser(@PathVariable userId: UUID): List<LoanDTO> {
        return loanInputPort.findByUser(userId).map { it.toDTO() }
    }

    @Post(value = "/submitOffer", produces = [APPLICATION_JSON])
    suspend fun submitOffer(@Body offerSubmissionDTO: OfferSubmissionDTO): OfferSubmissionResult {
        return loanInputPort.submitOffer(
            offer = offerSubmissionDTO.toOfferModel(),
            loanId = offerSubmissionDTO.loanId
        ).also {
            HttpResponse.created(it)
        }
    }
}
