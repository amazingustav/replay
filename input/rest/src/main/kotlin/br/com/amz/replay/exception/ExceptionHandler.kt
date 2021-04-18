package br.com.amz.replay.exception

import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.annotation.Produces
import javax.inject.Singleton
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.serverError
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor

@Produces
@Singleton
@Requirements(
    Requires(classes = [ResourceNotFoundException::class])
)
class ExceptionHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<RuntimeException, HttpResponse<*>> {

    override fun handle(
        request: HttpRequest<*>,
        exception: RuntimeException
    ): HttpResponse<Any> = when (exception) {
        is ResourceNotFoundException -> handleResourceNotFoundException(request, exception)
        else -> serverError()
    }

    private fun handleResourceNotFoundException(
        request: HttpRequest<*>,
        exception: ResourceNotFoundException
    ) = errorResponseProcessor.processResponse(
        ErrorContext.builder(request)
            .cause(exception)
            .errorMessage(exception.message ?: "Resource not found")
            .build(),
        HttpResponse.notFound<Any>()
    )
}