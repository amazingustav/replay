package br.com.amz.replay.vehicle.adapter

import br.com.amz.replay.vehicle.dbo.toDBO
import br.com.amz.replay.vehicle.model.Vehicle
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import br.com.amz.replay.vehicle.repository.VehicleRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class VehicleDataAccessAdapter(
    private val vehicleRepository: VehicleRepository
) : VehicleDataAccessPort {
    override suspend fun save(vehicle: Vehicle) = coroutineScope {
        vehicleRepository.save(vehicle.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findAll(): List<Vehicle> = coroutineScope {
        vehicleRepository.findAll()
            .asFlow()
            .toList()
            .map { it.toModel() }
    }

    override suspend fun findById(id: UUID) = coroutineScope {
        vehicleRepository.findById(id.toString())
            .awaitSingleOrNull()
            ?.toModel()
    }
}
