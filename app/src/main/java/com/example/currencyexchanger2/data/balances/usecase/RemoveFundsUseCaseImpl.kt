package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.balances.BalancesRepository
import com.example.currencyexchanger2.data.transactions.CurrencyAmount

class RemoveFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : RemoveFundsUseCase {
    override fun invoke(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.removeFunds(currency, amount)
        }
    }
}
