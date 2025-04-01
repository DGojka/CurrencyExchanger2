package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount

class AddFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : AddFundsUseCase {
    override fun invoke(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.addFunds(currency, amount)
        }
    }
}
