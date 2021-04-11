package br.com.amz.replay.vehicle.dbo

import br.com.amz.replay.vehicle.model.Vehicle
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("vehicle")
internal data class VehicleDBO(
    @Id
    val id: UUID,
    val make: String,
    val model: String,
    val year: Short,
    val milesAmount: Int
) {
    fun toModel() = Vehicle(
        id = id,
        make = make,
        model = model,
        year = year,
        milesAmount = milesAmount
    )
}

internal fun Vehicle.toDBO() = VehicleDBO(
    id = id ?: randomUUID(),
    make = make,
    model = model,
    year = year,
    milesAmount = milesAmount
)
