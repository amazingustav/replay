package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.ports.input.LoanInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import kotlinx.coroutines.coroutineScope
import java.util.UUID

@Controller("/loans")
class LoanController(
    private val loanInputPort: LoanInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        loanInputPort.findAll().map { it.toDTO() }
    }

    @Get(value = "/users/{userId}", produces = [APPLICATION_JSON])
    suspend fun findByUser(@PathVariable userId: UUID) = coroutineScope {
        loanInputPort.findByUser(userId).map { it.toDTO() }
    }
}
