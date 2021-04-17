package br.com.amz.replay.loan.ports.input

import br.com.amz.replay.loan.model.Loan

interface LoanInputPort {

    suspend fun findAll(): List<Loan>
}
