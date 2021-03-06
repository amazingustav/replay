package br.com.amz.replay.offer.dbo

import br.com.amz.replay.offer.model.Offer
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

    override fun equals(other: Any?) = this === other || (other is OfferDBO && id == other.id)

    override fun hashCode(): Int = id.hashCode()
}

internal fun Offer.toDBO() = OfferDBO(
    id = id,
    monthlyPaymentAmount = monthlyPaymentAmount,
    annualPercentageRate = annualPercentageRate,
    paymentAmount = paymentAmount
)