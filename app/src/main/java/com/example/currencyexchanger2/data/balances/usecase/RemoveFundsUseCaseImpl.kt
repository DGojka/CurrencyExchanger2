package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.balances.BalancesRepository

class RemoveFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : RemoveFundsUseCase {
    override fun invoke(
        currency: String,
        amount: Double,
    ) {
        repository.removeFunds(currency, amount)
    }
}
