package br.com.amz.replay.offer.dto

import br.com.amz.replay.offer.model.Offer

data class OfferDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val lenderName: String,
    val paymentAmount: Int
) {
    fun toModel() = Offer(
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        lenderName = lenderName,
        paymentAmount = paymentAmount
    )
}

fun Offer.toDTO() = OfferDTO(
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    lenderName = lenderName,
    paymentAmount = paymentAmount
)
