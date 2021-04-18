package br.com.amz.replay.offer.model

import java.util.UUID

data class Offer(
    val id: UUID? = null,
    val monthlyPaymentAmount: Double,
    val annualPercentageRate: Double,
    val paymentAmount: Int
)

data class ProposalOffer(
    val annualPercentageRate: Double,
    val monthlyPayment: Double,
    val timeRemaining: Int,
)