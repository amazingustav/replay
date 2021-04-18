package br.com.amz.replay.vehicle.adapter

import br.com.amz.replay.vehicle.dbo.toDBO
import br.com.amz.replay.vehicle.model.Vehicle
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import br.com.amz.replay.vehicle.repository.VehicleRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import javax.inject.Singleton

@Singleton
internal class VehicleDataAccessAdapter(
    private val vehicleRepository: VehicleRepository
) : VehicleDataAccessPort {

    override suspend fun save(vehicle: Vehicle): Vehicle {
        return vehicleRepository.save(vehicle.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findAll(): List<Vehicle> {
        return vehicleRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }
}
