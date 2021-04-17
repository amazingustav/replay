package br.com.amz.replay.user.dbo

import br.com.amz.replay.user.model.User
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("user")
data class UserDBO(
    @Id val id: UUID = randomUUID(),
    val name: String = "",
    @DateCreated var createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {

    fun toModel() = User(
        id = id,
        name = name
    )
}

internal fun User.toDBO() = UserDBO(
    id = id,
    name = name
)
