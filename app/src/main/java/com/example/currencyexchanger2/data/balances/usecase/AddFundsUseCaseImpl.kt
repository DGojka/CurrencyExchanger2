package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.balances.BalancesRepository

class AddFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : AddFundsUseCase {
    override fun invoke(
        currency: String,
        amount: Double,
    ) {
        repository.addFunds(currency, amount)
    }
}
