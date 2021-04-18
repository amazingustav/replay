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
    @Id val id: UUID = UUID.randomUUID(),
    val monthlyPaymentAmount: Double,
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