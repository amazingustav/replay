package br.com.amz.replay.vehicle.ports.input

import br.com.amz.replay.vehicle.model.Vehicle

interface VehicleInputPort {

    suspend fun findAll(): List<Vehicle>
}
