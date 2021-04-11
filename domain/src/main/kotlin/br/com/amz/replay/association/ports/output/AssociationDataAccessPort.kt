package br.com.amz.replay.association.ports.output

import br.com.amz.replay.association.model.UserVehicleAssociation
import br.com.amz.replay.association.model.UserVehicleId
import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

interface AssociationDataAccessPort {

    suspend fun save(association: UserVehicleAssociation): UserVehicleAssociation
    suspend fun findByUser(userId: UUID): List<UserVehicleAssociation>
    suspend fun findByVehicle(vehicleId: UUID): UserVehicleAssociation?
    suspend fun findById(id: UserVehicleId): UserVehicleAssociation?

}
