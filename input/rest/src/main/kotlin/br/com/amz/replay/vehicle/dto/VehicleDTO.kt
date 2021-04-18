package br.com.amz.replay.vehicle.dto

import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

data class VehicleDTO (
    val id: UUID,
    val make: String,
    val model: String,
    val year: Int,
    val milesAmount: Int
)

fun Vehicle.toDTO() = VehicleDTO(
    id = id,
    make = make,
    model = model,
    year = year,
    milesAmount = milesAmount
)
