package br.com.amz.replay.association.model

import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

data class UserVehicleAssociation(
    val user: User,
    val vehicle: Vehicle
)

data class UserVehicleId(
    val userId: UUID,
    val vehicleId: UUID
)
