package br.com.amz.replay.offer.dbo

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.model.Vehicle
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import javax.persistence.Id

@MappedEntity("offer")
data class OfferDBO(
    @Id val id: UUID = UUID.randomUUID(),
    val monthlyPaymentAmount: BigDecimal,
    val annualPercentageRate: Double,
    val paymentAmount: Int,
    @DateCreated val createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {
    fun toModel() = Offer(
        id = id,
        monthlyPaymentAmount = monthlyPaymentAmount,
        annualPercentageRate = annualPercentageRate,
        paymentAmount = paymentAmount,
    )
}

internal fun Offer.toDBO() = OfferDBO(
    id = id,
    monthlyPaymentAmount = monthlyPaymentAmount,
    annualPercentageRate = annualPercentageRate,
    paymentAmount = paymentAmount
)