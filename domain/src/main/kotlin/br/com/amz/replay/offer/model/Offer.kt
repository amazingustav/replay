package br.com.amz.replay.offer.model

import java.util.UUID

data class Offer(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val lenderName: String,
    val paymentAmount: Int
)
