package br.com.amz.replay.user.model

import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String,
    val email: String
)
