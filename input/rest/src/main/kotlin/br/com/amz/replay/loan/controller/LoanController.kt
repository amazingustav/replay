package br.com.amz.replay.loan.controller

import br.com.amz.replay.loan.dto.LoanInputDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.loan.ports.input.LoanInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.coroutineScope

@Controller("/loan")
class LoanController(
    private val loanInputPort: LoanInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body loanInputDTO: LoanInputDTO) = coroutineScope {
        loanInputPort.save(loanInputDTO.toModelInput()).toDTO()
    }
}
