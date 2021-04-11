package br.com.amz.replay.association.ports.input

import br.com.amz.replay.association.model.UserVehicleAssociation
import br.com.amz.replay.association.model.UserVehicleId
import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

interface AssociationInputPort {

    suspend fun save(userVehicleId: UserVehicleId): UserVehicleAssociation

    suspend fun findByUser(userId: UUID): List<Vehicle>

    suspend fun findByVehicle(vehicleId: UUID): User
}
