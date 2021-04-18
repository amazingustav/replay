package br.com.amz.replay.user.usecase

import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.input.UserInputPort
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import javax.inject.Singleton

@Singleton
class UserUseCase(
    private val userDataAccessPort: UserDataAccessPort,
) : UserInputPort {

    override suspend fun findAll(): List<User> = userDataAccessPort.findAll()
}
