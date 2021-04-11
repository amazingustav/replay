package br.com.amz.replay.user.adapter

import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.user.repository.UserRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class UserDataAccessAdapter(
    private val userRepository: UserRepository
) : UserDataAccessPort {
    override suspend fun save(user: User) = coroutineScope {
        userRepository.save(user.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findAll(): List<User> = coroutineScope {
        userRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }

    override suspend fun findById(id: UUID) = coroutineScope {
        userRepository.findById(id)
            .awaitSingleOrNull()
            ?.toModel()
    }
}
