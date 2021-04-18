package br.com.amz.replay.user.controller

import br.com.amz.replay.user.dto.UserDTO
import br.com.amz.replay.user.dto.toDTO
import br.com.amz.replay.user.ports.input.UserInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/users")
class UserController(
    private val userInputPort: UserInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll(): List<UserDTO> {
        return userInputPort.findAll().map { it.toDTO() }
    }
}
