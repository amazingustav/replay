package br.com.amz.replay.loan.dbo

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.offer.dbo.OfferDBO
import br.com.amz.replay.offer.dbo.toDBO
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.dbo.toDBO
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@MappedEntity("loan")
data class LoanDBO(
    @Id val id: UUID = UUID.randomUUID(),
    @OneToOne val vehicle: VehicleDBO,
    @ManyToOne val user: UserDBO,
    @OneToOne val offer: OfferDBO,
    val lenderName: String,
    val paidAmount: Int,
    val balance: BigDecimal,
    @DateCreated var createdAt: Instant = Instant.now(),
    @DateUpdated var modifiedAt: Instant? = null
) {
    fun toModel() = Loan(
        id = id,
        vehicle = vehicle.toModel(),
        offer = offer.toModel(),
        user = user.toModel(),
        lenderName = lenderName,
        paidAmount = paidAmount,
        balance = balance
    )

    override fun equals(other: Any?) = this === other || (other is LoanDBO && id == other.id)

    override fun hashCode(): Int = id.hashCode()
}

internal fun Loan.toDBO() = LoanDBO(
    id = id,
    vehicle = vehicle.toDBO(),
    user = user.toDBO(),
    offer = offer.toDBO(),
    lenderName = lenderName,
    paidAmount = paidAmount,
    balance = balance
)