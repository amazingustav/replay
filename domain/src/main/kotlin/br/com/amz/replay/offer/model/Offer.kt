package br.com.amz.replay.offer.model

import java.util.*

data class OfferInput(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val paymentAmount: Int
)

data class Offer(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val paymentAmount: Int
)
