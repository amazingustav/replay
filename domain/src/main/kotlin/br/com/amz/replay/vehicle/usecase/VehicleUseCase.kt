package br.com.amz.replay.vehicle.usecase

import br.com.amz.replay.vehicle.model.Vehicle
import br.com.amz.replay.vehicle.ports.input.VehicleInputPort
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import javax.inject.Singleton

@Singleton
class VehicleUseCase(
    private val vehicleDataAccessPort: VehicleDataAccessPort,
) : VehicleInputPort {

    override suspend fun findAll(): List<Vehicle> = vehicleDataAccessPort.findAll()
}
