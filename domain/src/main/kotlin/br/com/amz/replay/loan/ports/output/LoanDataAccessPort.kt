package br.com.amz.replay.loan.ports.output

import br.com.amz.replay.loan.model.Loan
import java.util.UUID

interface LoanDataAccessPort {

    suspend fun update(loan: Loan): Loan

    suspend fun findById(id: UUID): Loan?

    suspend fun findAll(): List<Loan>

    suspend fun findByUser(userId: UUID): List<Loan>
}
