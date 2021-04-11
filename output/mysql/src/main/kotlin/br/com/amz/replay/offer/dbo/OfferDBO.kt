package br.com.amz.replay.offer.dbo

import br.com.amz.replay.DBO
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("offer")
internal data class OfferDBO(
    @Id
    val id: UUID,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val user: UserDBO,
    val paymentAmount: Int,
): DBO() {
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
