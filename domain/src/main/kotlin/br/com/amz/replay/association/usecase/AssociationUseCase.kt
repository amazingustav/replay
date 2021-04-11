package br.com.amz.replay.association.usecase

import br.com.amz.replay.association.exception.ResourceNotFoundException
import br.com.amz.replay.association.model.UserVehicleAssociation
import br.com.amz.replay.association.model.UserVehicleId
import br.com.amz.replay.association.ports.input.AssociationInputPort
import br.com.amz.replay.association.ports.output.AssociationDataAccessPort
import br.com.amz.replay.user.model.User
import br.com.amz.replay.user.ports.output.UserDataAccessPort
import br.com.amz.replay.vehicle.model.Vehicle
import br.com.amz.replay.vehicle.ports.output.VehicleDataAccessPort
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import java.util.UUID
import javax.inject.Singleton

@Singleton
class AssociationUseCase(
    private val associationDataAccessPort: AssociationDataAccessPort,
    private val userDataAccessPort: UserDataAccessPort,
    private val vehicleDataAccessPort: VehicleDataAccessPort
) : AssociationInputPort {
    override suspend fun save(userVehicleId: UserVehicleId): UserVehicleAssociation = coroutineScope {
        logger.info("Saving association '$userVehicleId'")

        val user = userDataAccessPort.findById(userVehicleId.userId)
            ?: throw ResourceNotFoundException("No user found for ID ${userVehicleId.userId}")

        val vehicle = vehicleDataAccessPort.findById(userVehicleId.vehicleId)
            ?: throw ResourceNotFoundException("No vehicle found for ID ${userVehicleId.vehicleId}")

        associationDataAccessPort.save(
            UserVehicleAssociation(
                user = user,
                vehicle = vehicle
            )
        ).also {
            logger.info("Association between user '${it.user.name}' and vehicle '${it.vehicle.model}' saved")
        }
    }

    override suspend fun findByUser(userId: UUID): List<Vehicle> = coroutineScope {
        associationDataAccessPort.findByUser(userId).map {
            it.vehicle
        }
    }

    override suspend fun findByVehicle(vehicleId: UUID): User = coroutineScope {
        associationDataAccessPort.findByVehicle(vehicleId)?.user
            ?: throw ResourceNotFoundException("No user found for Vehicle ID $vehicleId")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
