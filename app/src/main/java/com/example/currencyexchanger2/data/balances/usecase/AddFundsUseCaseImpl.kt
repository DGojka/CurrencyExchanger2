package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.balances.BalancesRepository
import com.example.currencyexchanger2.data.transactions.CurrencyAmount

class AddFundsUseCaseImpl(
    private val repository: BalancesRepository,
) : AddFundsUseCase {
    override fun invoke(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.addFunds(currency, amount)
        }
    }
}
