package br.com.amz.replay.user.repository

import br.com.amz.replay.user.dbo.UserDBO
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import java.util.UUID

@R2dbcRepository(dialect = Dialect.MYSQL)
interface UserRepository: ReactorCrudRepository<UserDBO, UUID>
