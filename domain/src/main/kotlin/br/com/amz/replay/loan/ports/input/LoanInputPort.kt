package br.com.amz.replay.loan.ports.input

import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.model.LoanInput
import java.util.UUID

interface LoanInputPort {
    suspend fun save(loanInput: LoanInput): LoanOutput

    suspend fun findByUser(userId: UUID): List<LoanOutput>
}
