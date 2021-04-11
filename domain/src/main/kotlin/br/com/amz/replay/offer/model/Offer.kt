package br.com.amz.replay.offer.model

import br.com.amz.replay.loan.model.LoanOutput
import java.util.UUID

data class OfferInput(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val loanId: UUID,
    val lenderName: String,
    val paymentAmount: Int,
    val paidAmount: Int
)

data class Offer(
    val id: UUID? = null,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val loan: LoanOutput,
    val lenderName: String,
    val paymentAmount: Int,
    val paidAmount: Int
)
