package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.ports.input.LoanInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.coroutineScope

@Controller("/loan")
class LoanController(
    private val loanInputPort: LoanInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        loanInputPort.findAll().map { it.toDTO() }
    }
}
