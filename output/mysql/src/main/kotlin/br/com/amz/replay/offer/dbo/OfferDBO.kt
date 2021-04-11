package br.com.amz.replay.offer.dbo
import br.com.amz.replay.offer.model.Offer
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("offer")
internal data class OfferDBO(
    @Id
    val id: String,
    val annualPercentageRate: Double,
    val monthlyPaymentAmount: Double,
    val paymentAmount: Int
) {
    fun toModel() = Offer(
        id = UUID.fromString(id),
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        paymentAmount = paymentAmount
    )
}

internal fun Offer.toDBO() = OfferDBO(
    id = id?.toString() ?: randomUUID().toString(),
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    paymentAmount = paymentAmount
)
