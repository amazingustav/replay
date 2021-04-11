package br.com.amz.replay.offer.dbo
import br.com.amz.replay.DBO
import br.com.amz.replay.loan.dbo.LoanDBO
import br.com.amz.replay.loan.dbo.toDBO
import br.com.amz.replay.offer.model.Offer
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
    val loan: LoanDBO,
    val lenderName: String,
    val paymentAmount: Int,
    val paidAmount: Int
): DBO() {
    fun toModel() = Offer(
        id = id,
        annualPercentageRate = annualPercentageRate,
        monthlyPaymentAmount = monthlyPaymentAmount,
        loan = loan.toModel(),
        lenderName = lenderName,
        paymentAmount = paymentAmount,
        paidAmount = paidAmount
    )
}

internal fun Offer.toDBO() = OfferDBO(
    id = id ?: randomUUID(),
    annualPercentageRate = annualPercentageRate,
    monthlyPaymentAmount = monthlyPaymentAmount,
    loan = loan.toDBO(),
    lenderName = lenderName,
    paymentAmount = paymentAmount,
    paidAmount = paidAmount
)
