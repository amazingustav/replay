package br.com.amz.replay.offer.dto

import br.com.amz.replay.offer.model.Offer

data class OfferDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val paymentAmount: Int
) {
    fun toModel() = Offer(
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        paymentAmount = paymentAmount
    )
}

fun Offer.toDTO() = OfferDTO(
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    paymentAmount = paymentAmount
)
