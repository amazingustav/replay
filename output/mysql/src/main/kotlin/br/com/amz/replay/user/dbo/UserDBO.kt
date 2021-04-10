package br.com.amz.replay.user.dbo
import br.com.amz.replay.user.model.User
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("user")
internal data class UserDBO(
    @Id
    val id: String,
    val name: String,
    val email: String
) {
    fun toModel() = User(
        id = UUID.fromString(id),
        name = name,
        email = email
    )
}

internal fun User.toDBO() = UserDBO(
    id = id?.toString() ?: randomUUID().toString(),
    name = name,
    email = email
)
