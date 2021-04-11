package br.com.amz.replay.offer.dto

import br.com.amz.replay.loan.dto.LoanOutputDTO
import br.com.amz.replay.loan.dto.toDTO
import br.com.amz.replay.offer.model.OfferInput
import br.com.amz.replay.offer.model.Offer
import java.util.UUID

data class OfferInputDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val loanId: UUID,
    val lenderName: String,
    val paymentAmount: Int,
    val paidAmount: Int
) {
    fun toModel() = OfferInput(
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        loanId = loanId,
        lenderName = lenderName,
        paymentAmount = paymentAmount,
        paidAmount = paidAmount
    )
}

data class OfferOutputDTO (
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val loanOutputDTO: LoanOutputDTO,
    val lenderName: String,
    val paymentAmount: Int,
    val paidAmount: Int
)

fun Offer.toDTO() = OfferOutputDTO(
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    loanOutputDTO = loan.toDTO(),
    lenderName = lenderName,
    paymentAmount = paymentAmount,
    paidAmount = paidAmount
)
