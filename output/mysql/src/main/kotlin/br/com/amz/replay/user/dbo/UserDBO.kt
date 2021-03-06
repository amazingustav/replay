package br.com.amz.replay.user.dbo

import br.com.amz.replay.user.model.User
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.UUID
import javax.persistence.Id

@MappedEntity("user")
data class UserDBO(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String = "",
    @DateCreated var createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {

    fun toModel() = User(
        id = id,
        name = name
    )

    override fun equals(other: Any?) = this === other || (other is UserDBO && id == other.id)

    override fun hashCode(): Int = id.hashCode()
}
