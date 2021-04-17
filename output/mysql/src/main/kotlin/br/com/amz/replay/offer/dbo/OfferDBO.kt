package br.com.amz.replay.offer.dbo

import br.com.amz.replay.offer.model.Offer
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.UUID
import javax.persistence.Id

@MappedEntity("offer")
data class OfferDBO(
    @Id val id: UUID,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val paymentAmount: Int,
    @DateCreated val createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {
    fun toModel() = Offer(
        id = id,
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        paymentAmount = paymentAmount,
    )
}

internal fun Offer.toDBO() = OfferDBO(
    id = id ?: UUID.randomUUID(),
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    paymentAmount = paymentAmount
)
