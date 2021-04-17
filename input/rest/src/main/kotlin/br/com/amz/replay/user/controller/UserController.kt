package br.com.amz.replay.user.controller

import br.com.amz.replay.user.dto.toDTO
import br.com.amz.replay.user.ports.input.UserInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.coroutineScope

@Controller("/user")
class UserController(
    private val userInputPort: UserInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        userInputPort.findAll().map { it.toDTO() }
    }
}
