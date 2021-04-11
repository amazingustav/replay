package br.com.amz.replay.vehicle.controller

import br.com.amz.replay.vehicle.dto.VehicleDTO
import br.com.amz.replay.vehicle.dto.toDTO
import br.com.amz.replay.vehicle.ports.input.VehicleInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.coroutineScope

@Controller("/vehicle")
class VehicleController(
    private val vehicleInputPort: VehicleInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body vehicleDTO: VehicleDTO) = coroutineScope {
        vehicleInputPort.save(vehicleDTO.toModel()).toDTO()
    }

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = coroutineScope {
        vehicleInputPort.findAll().map { it.toDTO() }
    }
}
