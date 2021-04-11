package br.com.amz.replay.association.adapter

import br.com.amz.replay.association.dbo.toDBO
import br.com.amz.replay.association.model.UserVehicleAssociation
import br.com.amz.replay.association.model.UserVehicleId
import br.com.amz.replay.association.ports.output.AssociationDataAccessPort
import br.com.amz.replay.association.repository.AssociationRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import java.util.UUID
import javax.inject.Singleton

@Singleton
internal class AssociationDataAccessAdapter(
    private val associationRepository: AssociationRepository
) : AssociationDataAccessPort {
    override suspend fun save(association: UserVehicleAssociation) = coroutineScope {
        associationRepository.save(association.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findByUser(userId: UUID) = coroutineScope {
        associationRepository.findByIdUserId(userId)
            .asFlow()
            .toList()
            .map { it.toModel() }
    }

    override suspend fun findByVehicle(vehicleId: UUID) = coroutineScope {
        associationRepository.findByIdVehicleId(vehicleId)
            .awaitSingle()
            ?.toModel()
    }

    override suspend fun findById(id: UserVehicleId): UserVehicleAssociation? = coroutineScope {
        associationRepository.findByIdUserIdAndVehicleId(
            userId = id.userId.toString(),
            vehicleId = id.vehicleId.toString()
        ).awaitSingleOrNull()
            ?.toModel()
    }
}
