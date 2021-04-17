package br.com.amz.replay.loan.dbo

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.offer.dbo.OfferDBO
import br.com.amz.replay.offer.dbo.toDBO
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.dbo.toDBO
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
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
    val balance: Double,
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
}
