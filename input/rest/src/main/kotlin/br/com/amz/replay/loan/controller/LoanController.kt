package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.LoanInputDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.ports.input.LoanInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.*
import kotlinx.coroutines.coroutineScope
import java.util.UUID

@Controller("/loan")
class LoanController(
    private val loanInputPort: LoanInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body loanInputDTO: LoanInputDTO) = coroutineScope {
        loanInputPort.save(loanInputDTO.toModelInput()).toDTO()
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findByUser(
        @QueryValue("userId") userId: UUID
    ) = coroutineScope {
        loanInputPort.findByUser(userId).map { it.toDTO() }
    }
}
