package br.com.amz.replay.loan.ports.output

import br.com.amz.replay.loan.model.LoanOutput
import java.util.UUID

interface LoanDataAccessPort {

    suspend fun save(loanOutput: LoanOutput): LoanOutput

    suspend fun findById(id: UUID): LoanOutput?
}