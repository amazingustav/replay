package br.com.amz.replay.offer.model

import java.math.BigDecimal
import java.util.UUID

data class Offer(
    val id: UUID,
    val monthlyPaymentAmount: BigDecimal,
    val annualPercentageRate: Double,
    val paymentAmount: Int
)

data class ProposalOffer(
    val annualPercentageRate: Double,
    val monthlyPayment: BigDecimal,
    val timeRemaining: Int,
)