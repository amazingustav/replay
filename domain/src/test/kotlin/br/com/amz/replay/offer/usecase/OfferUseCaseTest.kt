package br.com.amz.replay.offer.usecase

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.junit5.annotation.MicronautTest

@MicronautTest
class OfferUseCaseTest(
    private val offerUseCase: OfferUseCase
): BehaviorSpec({

    given("um bolo de abacaxi") {
        `when`("me oferecerem") {
            val boloRuim = true

            then("eu jogo fora") {
                boloRuim shouldBe true
            }
        }
    }
})