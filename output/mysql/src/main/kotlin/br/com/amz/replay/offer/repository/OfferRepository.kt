package br.com.amz.replay.offer.repository

import br.com.amz.replay.offer.dbo.OfferDBO
import io.micronaut.data.annotation.Join
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import reactor.core.publisher.Flux
import java.util.*

@R2dbcRepository(dialect = Dialect.MYSQL)
interface OfferRepository: ReactorCrudRepository<OfferDBO, UUID> {

    @Join("user")
    override fun findAll(): Flux<OfferDBO>

    @Join("user")
    fun findByUserId(userId: UUID): Flux<OfferDBO>
}
