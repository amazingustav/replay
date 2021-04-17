package br.com.amz.replay.loan.repository

import br.com.amz.replay.loan.dbo.LoanDBO
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import java.util.UUID
import reactor.core.publisher.Flux
import io.micronaut.data.annotation.repeatable.JoinSpecifications

@R2dbcRepository(dialect = Dialect.MYSQL)
interface LoanRepository: ReactorCrudRepository<LoanDBO, UUID> {

    @JoinSpecifications(
        Join("user"),
        Join("vehicle")
    )
    override fun findAll(): Flux<LoanDBO>

    @JoinSpecifications(
        Join("user"),
        Join("vehicle")
    )
    fun findByUserId(userId: UUID): Flux<LoanDBO>
}