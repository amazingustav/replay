package br.com.amz.replay.user.dto

import br.com.amz.replay.user.model.User
import java.util.UUID

data class UserDTO (
    val id: UUID,
    val name: String
)

fun User.toDTO() = UserDTO(
    id = id,
    name = name
)
