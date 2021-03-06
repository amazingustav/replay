package br.com.amz.replay.loan.repository

import br.com.amz.replay.loan.dbo.LoanDBO
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.repeatable.JoinSpecifications
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@R2dbcRepository(dialect = Dialect.MYSQL)
interface LoanRepository: ReactorCrudRepository<LoanDBO, UUID> {

    @JoinSpecifications(
        Join("vehicle"),
        Join("user"),
        Join("offer")
    )
    override fun findAll(): Flux<LoanDBO>

    @JoinSpecifications(
        Join("vehicle"),
        Join("user"),
        Join("offer")
    )
    override fun findById(id: UUID): Mono<LoanDBO>

    @JoinSpecifications(
        Join("vehicle"),
        Join("user"),
        Join("offer")
    )
    fun findByUserId(userId: UUID): Flux<LoanDBO>

    @Query(nativeQuery = true, value = "UPDATE loan l set l.offer_id = :offerId where l.id = :loanId")
    fun updateOffer(offerId: UUID, loanId: UUID)
}