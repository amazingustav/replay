package br.com.amz.replay.user.dto

import br.com.amz.replay.user.model.User

data class UserDTO (
    val name: String
)

fun User.toDTO() = UserDTO(
    name = name
)
