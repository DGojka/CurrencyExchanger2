package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import kotlinx.coroutines.flow.StateFlow

class ManageFundsUseCaseImpl(private val repository: BalancesRepository) : ManageFundsUseCase {
    override fun addFunds(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.addFunds(currency, amount)
        }
    }

    override fun removeFunds(currencyAmount: CurrencyAmount) {
        with(currencyAmount) {
            repository.removeFunds(currency, amount)
        }
    }

    override fun getBalances(): StateFlow<Map<String, Double>> = repository.getBalances()
}