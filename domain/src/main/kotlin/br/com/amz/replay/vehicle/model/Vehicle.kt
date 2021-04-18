package br.com.amz.replay.vehicle.model

import java.util.UUID

data class Vehicle(
    val id: UUID,
    val make: String,
    val model: String,
    val year: Int,
    val milesAmount: Int
)
