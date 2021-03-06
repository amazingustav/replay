package br.com.amz.replay.loan.model

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.math.BigDecimal
import java.util.UUID

data class Loan(
    val id: UUID,
    val vehicle: Vehicle,
    val offer: Offer,
    val user: User,
    val lenderName: String,
    val paidAmount: Int,
    val balance: BigDecimal
)

data class OfferSubmissionResult(
    val title: String,
    val message: String
)