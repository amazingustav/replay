package br.com.amz.replay.loan

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.loan.usecase.LoanUseCase
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import br.com.amz.replay.util.createMockLoan
import br.com.amz.replay.util.createMockOffer
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
@ExperimentalCoroutinesApi
class LoanUseCaseTest {

    @InjectMockKs private lateinit var loanUseCase: LoanUseCase
    @MockK private lateinit var loanDataAccessPort: LoanDataAccessPort
    @MockK private lateinit var offerDataAccessPort: OfferDataAccessPort

    @Test
    fun `should throw exception due to invalid loanId when submit offer`() = runBlockingTest {
        // Given
        val offer = createMockOffer()

        coEvery { offerDataAccessPort.save(eq(offer)) } returns offer
        coEvery { loanDataAccessPort.findById(any()) } returns null

        // Then
        assertThrows<ResourceNotFoundException> {
            // When
            loanUseCase.submitOffer(offer, UUID.randomUUID())
        }
    }

    @Test
    fun `should return offer submission success message when submit offer`() = runBlockingTest {
        // Given
        val offer = createMockOffer()
        val loan = createMockLoan()

        coEvery { offerDataAccessPort.save(eq(offer)) } returns offer
        coEvery { loanDataAccessPort.findById(eq(loan.id)) } returns loan
        coJustRun { loanDataAccessPort.update(any()) }

        // When
        val offerSubmissionResult = loanUseCase.submitOffer(offer, loan.id)

        // Then
        assertEquals("Congratulations", offerSubmissionResult.title)
        assert(offerSubmissionResult.message.contains("You're about to lower your monthly auto loan payment!"))
    }

    @Test
    fun `should replace loan's old offer for the offer to submit when submit offer`() = runBlockingTest {
        // Given
        val offer = Offer(
            id = UUID.randomUUID(),
            monthlyPaymentAmount = 300.00.toBigDecimal(),
            annualPercentageRate = 7.1,
            paymentAmount = 84
        )
        
        val loan = createMockLoan()
        val loanSlot = slot<Loan>()

        coEvery { offerDataAccessPort.save(eq(offer)) } returns offer
        coEvery { loanDataAccessPort.findById(eq(loan.id)) } returns loan
        coJustRun { loanDataAccessPort.update(capture(loanSlot)) }

        // When
        loanUseCase.submitOffer(offer, loan.id)

        // Then
        with(loanSlot.captured.offer) {
            assertEquals(offer.monthlyPaymentAmount, this.monthlyPaymentAmount)
            assertEquals(offer.annualPercentageRate, this.annualPercentageRate)
            assertEquals(offer.paymentAmount, this.paymentAmount)
        }
    }
}
