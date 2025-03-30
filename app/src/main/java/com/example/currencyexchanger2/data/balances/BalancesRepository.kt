package com.example.currencyexchanger2.data.balances

import kotlinx.coroutines.flow.StateFlow

interface BalancesRepository {
    fun addFunds(
        currency: String,
        amount: Double,
    )

    fun removeFunds(
        currency: String,
        amount: Double,
    )

    fun getBalances(): StateFlow<Map<String, Double>>
}
