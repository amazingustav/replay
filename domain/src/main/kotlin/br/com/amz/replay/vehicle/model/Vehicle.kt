package br.com.amz.replay.vehicle.model

import java.util.UUID

data class Vehicle(
    val id: UUID? = null,
    val make: String,
    val model: String,
    val year: Short,
    val milesAmount: Int
)
