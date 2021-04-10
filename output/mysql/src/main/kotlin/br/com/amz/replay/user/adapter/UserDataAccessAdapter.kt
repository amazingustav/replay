package br.com.amz.replay.user.adapter

import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.user.repository.UserRepository
import javax.inject.Singleton
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle

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
        userRepository
            .findAll()
            .asFlow()
            .toList()
            .map { personDBO ->
                personDBO.toModel()
            }
    }
}
