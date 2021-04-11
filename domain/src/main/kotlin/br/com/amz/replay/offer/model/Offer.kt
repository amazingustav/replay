package br.com.amz.replay.offer.model

import br.com.amz.replay.user.model.User
import java.util.UUID

data class OfferInput(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val userId: UUID,
    val paymentAmount: Int
)

data class Offer(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val user: User,
    val paymentAmount: Int
)
