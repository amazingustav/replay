package br.com.amz.replay.association.dto

import br.com.amz.replay.association.model.UserVehicleId
import java.util.UUID

data class AssociationDTO(
    val userId: UUID,
    val vehicleId: UUID
) {
    fun toModel() = UserVehicleId(
        userId = userId,
        vehicleId = vehicleId
    )
}
