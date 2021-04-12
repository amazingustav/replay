package br.com.amz.replay.loan.dbo

import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.dbo.toDBO
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.*

@MappedEntity("loan")
data class LoanDBO(
    @Id
    val id: UUID = randomUUID(),
    @ManyToOne
    val user: UserDBO,
    @OneToOne
    val vehicle: VehicleDBO,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double,
    @DateCreated
    var createdAt: Instant = Instant.now(),
    @DateUpdated
    var modifiedAt: Instant? = null
) {
    fun toModel() = LoanOutput(
        id = id,
        user = user.toModel(),
        vehicle = vehicle.toModel(),
        lenderName = lenderName,
        paidAmount = paidAmount,
        balance = balance
    )
}

internal fun LoanOutput.toDBO() = LoanDBO(
    id = id ?: randomUUID(),
    user = user.toDBO(),
    vehicle = vehicle.toDBO(),
    lenderName = lenderName,
    paidAmount = paidAmount,
    balance = balance
)
