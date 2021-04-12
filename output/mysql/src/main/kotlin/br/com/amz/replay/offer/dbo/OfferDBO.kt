package br.com.amz.replay.offer.dbo

import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("offer")
data class OfferDBO(
    @Id
    val id: UUID,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = [Relation.Cascade.ALL])
    val user: UserDBO,
    val paymentAmount: Int,
    @DateCreated
    val createdAt: Instant = Instant.now(),
    @DateUpdated
    var modifiedAt: Instant? = null
) {
    fun toModel() = Offer(
        id = id,
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        user = user.toModel(),
        paymentAmount = paymentAmount,
    )
}

internal fun Offer.toDBO() = OfferDBO(
    id = id ?: randomUUID(),
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    user = user.toDBO(),
    paymentAmount = paymentAmount,
)
