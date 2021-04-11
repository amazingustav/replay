package br.com.amz.replay.user.ports.output

import br.com.amz.replay.user.model.User
import java.util.UUID

interface UserDataAccessPort {

    suspend fun save(user: User): User

    suspend fun findAll(): List<User>

    suspend fun findById(id: UUID): User?
}
