package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount

class RemoveFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : RemoveFundsUseCase {
    override fun invoke(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.removeFunds(currency, amount)
        }
    }
}
