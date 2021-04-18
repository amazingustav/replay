package br.com.amz.replay.offer.usecase

import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.util.createMockLoan
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
@ExperimentalCoroutinesApi
class OfferUseCaseTest {

    @InjectMockKs private lateinit var offerUseCase: OfferUseCase
    @MockK private lateinit var offerDataAccessPort: OfferDataAccessPort
    @MockK private lateinit var loanDataAccessPort: LoanDataAccessPort

    @Test
    fun `should return 3 proposal offers with different time remaining when generate offer`() = runBlockingTest {
        // Given
        val loan = createMockLoan()

        coEvery {
            loanDataAccessPort.findById(eq(loan.id))
        } returns loan

        // When
        val proposalOffers = offerUseCase.generateProposalOffers(loan.id)

        // Then
        assert(proposalOffers.size == 3)
        assert(proposalOffers.any { it.timeRemaining == 60 } )
        assert(proposalOffers.any { it.timeRemaining == 72 } )
        assert(proposalOffers.any { it.timeRemaining == 84 } )
    }

    @Test
    fun `should return proposal offers with 50 percent of discount in APR when generate offer`() = runBlockingTest {
        // Given
        val loan = createMockLoan()

        coEvery {
            loanDataAccessPort.findById(eq(loan.id))
        } returns loan

        // When
        val proposalOffers = offerUseCase.generateProposalOffers(loan.id)
        val aprWithDiscount = loan.offer.annualPercentageRate * 0.5

        // Then
        assert(proposalOffers.all { it.annualPercentageRate == aprWithDiscount } )
    }

    @Test
    fun `should generate proposal offers with given APR and loan balance when generate offer`() = runBlockingTest {
        // Given
        val offer = Offer(                                 // Given the following scenario:
            id = UUID.randomUUID(),
            monthlyPaymentAmount = 0.0.toBigDecimal(),     // Balance - R$ 10.000,00
            annualPercentageRate = 7.5,                    // APR - 7.5% per year / 12 months = 0.625 (and finally 0.00625 as BigDecimal)
            paymentAmount = 0                              // Time remaining - 60/72/84 months
        )

        val loan = createMockLoan(offer)

        coEvery {
            loanDataAccessPort.findById(eq(loan.id))
        } returns loan

        // When
        val proposalOffers = offerUseCase.generateProposalOffers(loan.id)

        val offer60months = proposalOffers.first { it.timeRemaining == 60 }
        val offer72months = proposalOffers.first { it.timeRemaining == 72 }
        val offer84months = proposalOffers.first { it.timeRemaining == 84 }

        // Then
        assert(offer60months.monthlyPayment == 183.04.toBigDecimal())
        assert(offer72months.monthlyPayment == 155.32.toBigDecimal())
        assert(offer84months.monthlyPayment == 135.54.toBigDecimal())
    }
}
