package br.com.amz.replay.vehicle.usecase

import br.com.amz.replay.vehicle.model.Vehicle
import br.com.amz.replay.vehicle.ports.input.VehicleInputPort
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class VehicleUseCase(
    private val vehicleDataAccessPort: VehicleDataAccessPort,
) : VehicleInputPort {

    override suspend fun save(vehicle: Vehicle) = coroutineScope {
        vehicleDataAccessPort.save(vehicle).also {
            logger.info("Vehicle saved. Id: ${it.id}")
        }
    }

    override suspend fun findAll(): List<Vehicle> = coroutineScope {
        vehicleDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
