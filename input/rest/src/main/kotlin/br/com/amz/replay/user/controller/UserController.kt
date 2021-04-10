package br.com.amz.replay.user.controller

import br.com.amz.replay.user.dto.UserDTO
import br.com.amz.replay.user.dto.toDTO
import br.com.amz.replay.user.ports.input.UserInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.coroutineScope

@Controller("/user")
class UserController(
    private val userInputPort: UserInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body userDTO: UserDTO) = coroutineScope {
        userInputPort.save(userDTO.toModel()).toDTO()
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        userInputPort.findAll().map { it.toDTO() }
    }
}
