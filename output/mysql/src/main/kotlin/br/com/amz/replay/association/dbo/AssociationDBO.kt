package br.com.amz.replay.association.dbo
import br.com.amz.replay.association.model.UserVehicleAssociation
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.dbo.toDBO
import io.micronaut.data.annotation.Embeddable
import io.micronaut.data.annotation.EmbeddedId
import io.micronaut.data.annotation.MappedEntity

@MappedEntity("user_vehicle_association")
internal data class UserVehicleAssociationDBO(
    @EmbeddedId
    val id: UserVehicleIdDBO
) {
    fun toModel() = UserVehicleAssociation(
        user = id.user.toModel(),
        vehicle = id.vehicle.toModel()
    )
}

@Embeddable
internal data class UserVehicleIdDBO(
    val user: UserDBO,
    val vehicle: VehicleDBO
)

internal fun UserVehicleAssociation.toDBO() = UserVehicleAssociationDBO(
    id = UserVehicleIdDBO(
        user = user.toDBO(),
        vehicle = vehicle.toDBO()
    )
)
