package br.com.amz.replay.offer.usecase

import br.com.amz.replay.exception.ResourceNotFoundException
import br.com.amz.replay.loan.ports.output.LoanDataAccessPort
import br.com.amz.replay.offer.model.ProposalOffer
import br.com.amz.replay.offer.ports.input.OfferInputPort
import br.com.amz.replay.offer.ports.output.OfferDataAccessPort
import kotlinx.coroutines.coroutineScope
import java.util.UUID
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class OfferUseCase(
    private val offerDataAccessPort: OfferDataAccessPort,
    private val loanDataAccessPort: LoanDataAccessPort
) : OfferInputPort {

    override suspend fun findAll() = coroutineScope {
        offerDataAccessPort.findAll()
    }

    override suspend fun generateProposalOffers(loanId: UUID): List<ProposalOffer> {
        val loan = loanDataAccessPort.findById(loanId)
            ?: throw ResourceNotFoundException("Loan not found while generate proposal offers")

        val proposalOffers: MutableList<ProposalOffer> = mutableListOf()
        val aprWithDiscount = loan.offer.annualPercentageRate * APR_PERCENTAGE_DISCOUNT

        monthRemainingProposals.forEach {
            proposalOffers.add(
                ProposalOffer(
                    annualPercentageRate = aprWithDiscount,
                    monthlyPayment = calculateLoanPayment(
                        balance = loan.balance,
                        apr = aprWithDiscount,
                        monthsRemaining = it
                    ),
                    timeRemaining = it
                )
            )
        }

        return proposalOffers
    }

    /**
     * This function calculates the offer monthly payment. To do this, it uses the formula below:
     * MP = LB * APR / 12 * (1 + APR / 12)^n / ((1 + APR / 12)^n - 1), where:
     *
     * MP = Monthly Payment
     * LB = Loan Balance
     * APR = Annual Percentage Rate
     * N = Number of months
     * */
    private fun calculateLoanPayment(balance: Double, apr: Double, monthsRemaining: Int): Double {
        val ratePerPeriod = 1 + apr / 12
        val monthlyPayment = ratePerPeriod.pow(monthsRemaining) / (ratePerPeriod.pow(monthsRemaining) - 1)

        return balance * (apr / 12) * monthlyPayment
    }

    companion object {
        private val monthRemainingProposals = listOf(60, 72, 84)
        private const val APR_PERCENTAGE_DISCOUNT = 0.5
    }
}
