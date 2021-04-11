package br.com.amz.replay.loan.dto

import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.model.LoanInput
import br.com.amz.replay.user.dto.UserDTO
import br.com.amz.replay.user.dto.toDTO
import br.com.amz.replay.vehicle.dto.VehicleDTO
import br.com.amz.replay.vehicle.dto.toDTO
import java.util.UUID

data class LoanInputDTO (
    val userId: UUID,
    val vehicleId: UUID,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
) {
    fun toModelInput() = LoanInput(
        userId = userId,
        vehicleId = vehicleId,
        lenderName = lenderName,
        paidAmount = paidAmount,
        balance = balance
    )
}

data class LoanOutputDTO (
    val user: UserDTO,
    val vehicle: VehicleDTO,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
)

fun LoanOutput.toDTO() = LoanOutputDTO(
    user = user.toDTO(),
    vehicle = vehicle.toDTO(),
    lenderName = lenderName,
    paidAmount = paidAmount,
    balance = balance
)
