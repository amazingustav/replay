package br.com.amz.replay.association.controller

import br.com.amz.replay.association.dto.AssociationDTO
import br.com.amz.replay.association.usecase.AssociationUseCase
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/association")
class AssociationController(
    private val associationUseCase: AssociationUseCase
) {

    @Post(produces = [APPLICATION_JSON])
    suspend fun association(
        @Body associationDTO: AssociationDTO
    ) {
        associationUseCase.save(associationDTO.toModel())
    }
}

