package br.com.amz.replay.offer.dto

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.model.ProposalOffer
import java.util.UUID

data class OfferDTO (
    val id: UUID,
    val monthlyPaymentAmount: Double,
    val annualPercentageRate: Double,
    val paymentAmount: Int,
)

fun Offer.toDTO() = OfferDTO(
    id = id,
    monthlyPaymentAmount = monthlyPaymentAmount,
    annualPercentageRate = annualPercentageRate,
    paymentAmount = paymentAmount
)

data class ProposalOfferDTO (
    val annualPercentageRate: Double,
    val monthlyPayment: Double,
    val timeRemaining: Int
)

fun ProposalOffer.toDTO() = ProposalOfferDTO(
    annualPercentageRate = annualPercentageRate,
    monthlyPayment = monthlyPayment,
    timeRemaining = timeRemaining
)
