package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.LoanInputDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.ports.input.LoanInputPort
import br.com.amz.replay.user.dto.toDTO
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

    @Get(value = "/userId/{userId}", produces = [APPLICATION_JSON])
    suspend fun findByUser(@PathVariable("userId") userId: UUID) = coroutineScope {
        loanInputPort.findByUser(userId).map { it.toDTO() }
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        loanInputPort.findAll().map { it.toDTO() }
    }
}
