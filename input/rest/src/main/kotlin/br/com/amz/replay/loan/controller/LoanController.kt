package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.LoanDTO
import br.com.amz.replay.loan.dto.OfferSubmissionDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.offer.dto.OfferDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
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

    @Get(value = "/submitOffer", produces = [APPLICATION_JSON])
    suspend fun submitOffer(@Body offerSubmissionDTO: OfferSubmissionDTO): MutableHttpResponse<out Any> {
        return loanInputPort.submitOffer(
            offer = offerSubmissionDTO.toOfferModel(),
            loanId = offerSubmissionDTO.loanId
        ).let {
            HttpResponse.created(it)
        }
    }
}
