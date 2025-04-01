package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import kotlinx.coroutines.flow.StateFlow

interface ManageFundsUseCase {
    fun addFunds(currencyAmount: CurrencyAmount)

    fun removeFunds(currencyAmount: CurrencyAmount)

    fun getBalances(): StateFlow<Map<String, Double>>
}