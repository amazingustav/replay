package br.com.amz.replay.util

import br.com.amz.replay.loan.model.Loan
import br.com.amz.replay.offer.model.Offer
import br.com.amz.replay.user.model.User
import br.com.amz.replay.vehicle.model.Vehicle
import java.util.UUID

internal fun createMockLoan(
    offer: Offer = createMockOffer(),
    user: User = createMockUser(),
    vehicle: Vehicle = createMockVehicle()
) = Loan(
    id = UUID.randomUUID(),
    lenderName = "Test Bank",
    paidAmount = 10,
    balance = 10_000.00.toBigDecimal(),
    offer = offer,
    user = user,
    vehicle = vehicle
)

internal fun createMockOffer() = Offer(
    id = UUID.randomUUID(),
    monthlyPaymentAmount = 100.00.toBigDecimal(),
    annualPercentageRate = 14.2,
    paymentAmount = 72
)

internal fun createMockUser() = User(
    id = UUID.randomUUID(),
    name = "Test User"
)

internal fun createMockVehicle() = Vehicle(
    id = UUID.randomUUID(),
    make = "Fiat",
    model = "Uno",
    year = 1999,
    milesAmount = 230_000
)

