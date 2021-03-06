package br.com.amz.replay.vehicle.dbo

import br.com.amz.replay.vehicle.model.Vehicle
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.UUID
import javax.persistence.Id

@MappedEntity("vehicle")
data class VehicleDBO(
    @Id val id: UUID = UUID.randomUUID(),
    val make: String = "",
    val model: String = "",
    val year: Int = 0,
    val milesAmount: Int = 0,
    @DateCreated var createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {
    fun toModel() = Vehicle(
        id = id,
        make = make,
        model = model,
        year = year,
        milesAmount = milesAmount
    )

    override fun equals(other: Any?) = this === other || (other is VehicleDBO && id == other.id)

    override fun hashCode(): Int = id.hashCode()
}

internal fun Vehicle.toDBO() = VehicleDBO(
    id = id,
    make = make,
    model = model,
    year = year,
    milesAmount = milesAmount
)
