package br.com.amz.replay.vehicle.controller

import br.com.amz.replay.vehicle.dto.toDTO
import br.com.amz.replay.vehicle.ports.input.VehicleInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.coroutineScope

@Controller("/vehicle")
class VehicleController(
    private val vehicleInputPort: VehicleInputPort
) {

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        vehicleInputPort.findAll().map { it.toDTO() }
    }
}
