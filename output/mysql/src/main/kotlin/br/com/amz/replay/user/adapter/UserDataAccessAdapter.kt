package br.com.amz.replay.user.adapter

import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.user.repository.UserRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import javax.inject.Singleton

@Singleton
internal class UserDataAccessAdapter(
    private val userRepository: UserRepository
) : UserDataAccessPort {

    override suspend fun findAll(): List<User> {
        return userRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}
