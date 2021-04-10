package br.com.amz.replay.user.dto

import br.com.amz.replay.user.model.User

data class UserDTO (
    val name: String,
    val email: String
) {
    fun toModel() = User(
        name = name,
        email = email
    )
}

fun User.toDTO() = UserDTO(
    name = name,
    email = email
)
