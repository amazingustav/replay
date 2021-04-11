package br.com.amz.replay.user.dbo
import br.com.amz.replay.DBO
import br.com.amz.replay.user.model.User
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("user")
internal data class UserDBO(
    @Id
    val id: UUID,
    val name: String
): DBO() {
    fun toModel() = User(
        id = id,
        name = name
    )
}

internal fun User.toDBO() = UserDBO(
    id = id ?: randomUUID(),
    name = name
)
