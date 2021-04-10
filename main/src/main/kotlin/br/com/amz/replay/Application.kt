package br.com.amz.replay

import io.micronaut.runtime.mapError
import io.micronaut.runtime.startApplication

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            startApplication<Application>(*args) {
                packages("br.com.amz.replay")
                mapError<RuntimeException> { 500 }
            }
        }
    }
}

