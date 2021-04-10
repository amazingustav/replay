package br.com.amz.replay.user.ports.output

import br.com.amz.replay.user.model.User

interface UserDataAccessPort {
    suspend fun save(user: User): User
    suspend fun findAll(): List<User>
}
