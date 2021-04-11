package br.com.amz.replay.loan.dbo

import br.com.amz.replay.DBO
import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.user.dbo.UserDBO
import br.com.amz.replay.user.dbo.toDBO
import br.com.amz.replay.vehicle.dbo.VehicleDBO
import br.com.amz.replay.vehicle.dbo.toDBO
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("loan")
internal data class LoanDBO(
    @Id
    val id: UUID,
    val user: UserDBO,
    val vehicle: VehicleDBO,
    val lenderName: String,
    val paidAmount: Int,
    val balance: Double
): DBO() {
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
