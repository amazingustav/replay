package br.com.amz.replay.loan.repository

import br.com.amz.replay.loan.dbo.LoanDBO
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import java.util.UUID

@R2dbcRepository(dialect = Dialect.MYSQL)
internal interface LoanRepository: ReactorCrudRepository<LoanDBO, UUID>
