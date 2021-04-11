package br.com.amz.replay.association.repository

import br.com.amz.replay.association.dbo.UserVehicleAssociationDBO
import br.com.amz.replay.association.dbo.UserVehicleIdDBO
import br.com.amz.replay.offer.dbo.OfferDBO
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@R2dbcRepository(dialect = Dialect.MYSQL)
internal interface AssociationRepository: ReactorCrudRepository<UserVehicleAssociationDBO, UserVehicleIdDBO> {

    suspend fun findByIdUserId(userId: UUID): Flux<UserVehicleAssociationDBO>
    suspend fun findByIdVehicleId(vehicleId: UUID): Mono<UserVehicleAssociationDBO?>
    suspend fun findByIdUserIdAndVehicleId(userId: String, vehicleId: String): Mono<UserVehicleAssociationDBO?>
}
