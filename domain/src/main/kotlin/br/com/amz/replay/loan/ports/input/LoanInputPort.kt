package br.com.amz.replay.loan.ports.input

import br.com.amz.replay.loan.model.Loan
import java.util.UUID

interface LoanInputPort {

    suspend fun findAll(): List<Loan>

    suspend fun findByUser(userId: UUID): List<Loan>
}
