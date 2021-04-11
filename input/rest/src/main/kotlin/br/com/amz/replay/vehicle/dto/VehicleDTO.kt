package br.com.amz.replay.vehicle.dto

import br.com.amz.replay.vehicle.model.Vehicle

data class VehicleDTO (
    val make: String,
    val model: String,
    val year: Short,
    val milesAmount: Int
) {
    fun toModel() = Vehicle(
        make = make,
        model = model,
        year = year,
        milesAmount = milesAmount
    )
}

fun Vehicle.toDTO() = VehicleDTO(
    make = make,
    model = model,
    year = year,
    milesAmount = milesAmount
)
