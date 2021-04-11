package br.com.amz.replay.loan.ports.input

import br.com.amz.replay.loan.model.LoanOutput
import br.com.amz.replay.loan.model.LoanInput

interface LoanInputPort {
    suspend fun save(loanInput: LoanInput): LoanOutput
}
