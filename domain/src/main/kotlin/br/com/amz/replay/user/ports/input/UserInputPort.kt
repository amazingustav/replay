package br.com.amz.replay.user.ports.input

import br.com.amz.replay.user.model.User

interface UserInputPort {

    suspend fun findAll(): List<User>
}
