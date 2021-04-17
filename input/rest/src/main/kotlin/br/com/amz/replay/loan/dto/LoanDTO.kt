package br.com.amz.replay.loan.dto

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.offer.dto.OfferDTO
import br.com.amz.replay.offer.dto.toDTO
import br.com.amz.replay.user.dto.UserDTO
import br.com.amz.replay.user.dto.toDTO
import br.com.amz.replay.vehicle.dto.VehicleDTO
import br.com.amz.replay.vehicle.dto.toDTO

data class LoanDTO (
    val vehicle: VehicleDTO,
    val user: UserDTO,
    val offer: OfferDTO,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
)

fun Loan.toDTO() = LoanDTO(
    vehicle = vehicle.toDTO(),
    user = user.toDTO(),
    offer = offer.toDTO(),
    lenderName = lenderName,
    paidAmount = paidAmount,
    balance = balance
)
