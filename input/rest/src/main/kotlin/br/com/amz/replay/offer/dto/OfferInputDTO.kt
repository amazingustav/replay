package br.com.amz.replay.offer.dto

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.user.dto.UserDTO
import br.com.amz.replay.user.dto.toDTO
import java.util.UUID

data class OfferInputDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val userId: UUID,
    val paymentAmount: Int
) {
    fun toModel() = OfferInput(
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        userId = userId,
        paymentAmount = paymentAmount
    )
}

data class OfferOutputDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val user: UserDTO,
    val paymentAmount: Int,
)

fun Offer.toDTO() = OfferOutputDTO(
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    user = user.toDTO(),
    paymentAmount = paymentAmount
)
