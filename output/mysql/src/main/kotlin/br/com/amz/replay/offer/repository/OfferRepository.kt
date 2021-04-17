package br.com.amz.replay.offer.repository

import br.com.amz.replay.offer.dbo.OfferDBO
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.repeatable.JoinSpecifications
import io.micronaut.data.jpa.annotation.EntityGraph
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

@R2dbcRepository(dialect = Dialect.MYSQL)
interface OfferRepository: ReactorCrudRepository<OfferDBO, UUID> {

    override fun findAll(): Flux<OfferDBO>
}
