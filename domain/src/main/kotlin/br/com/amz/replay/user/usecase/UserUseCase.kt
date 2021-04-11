package br.com.amz.replay.user.usecase

import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.input.UserInputPort
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import javax.inject.Singleton
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory

@Singleton
class UserUseCase(
    private val userDataAccessPort: UserDataAccessPort,
) : UserInputPort {
    override suspend fun save(user: User) = coroutineScope {
        logger.info("Saving user: $user")

        userDataAccessPort.save(user).also {
            logger.info("User saved: $it")
        }
    }

    override suspend fun findAll(): List<User> = coroutineScope {
        userDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
