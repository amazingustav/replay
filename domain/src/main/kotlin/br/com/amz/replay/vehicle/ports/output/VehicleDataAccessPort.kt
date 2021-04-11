package br.com.amz.replay.vehicle.ports.output

import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

interface VehicleDataAccessPort {

    suspend fun save(vehicle: Vehicle): Vehicle

    suspend fun findAll(): List<Vehicle>

    suspend fun findById(id: UUID): Vehicle?
}
