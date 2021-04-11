package br.com.amz.replay.loan.model

import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

data class LoanOutput(
    val id: UUID? = null,
    val user: User,
    val vehicle: Vehicle,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
)

data class LoanInput(
    val userId: UUID,
    val vehicleId: UUID,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
)
