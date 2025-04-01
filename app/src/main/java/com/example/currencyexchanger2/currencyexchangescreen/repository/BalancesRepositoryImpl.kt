package com.example.currencyexchanger2.currencyexchangescreen.repository

import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.BalancesManager
import kotlinx.coroutines.flow.StateFlow

class BalancesRepositoryImpl(
    private val balancesManager: BalancesManager,
) : BalancesRepository {
    override fun addFunds(
        currency: String,
        amount: Double,
    ) {
        require(amount > 0) { "Receive amount must be positive." }

        val balances = balancesManager.balancesFlow.value.toMutableMap()
        balances[currency] = (balances[currency] ?: 0.0) + amount

        balancesManager.updateBalances(balances)
    }

    override fun removeFunds(
        currency: String,
        amount: Double,
    ) {
        require(amount > 0) { "Sell amount must be positive." }

        val balances = balancesManager.balancesFlow.value.toMutableMap()
        val currentAmount = balances[currency] ?: 0.0

        require(currentAmount >= amount) { "Insufficient balance for currency: $currency." }

        balances[currency] = currentAmount - amount
        if (balances[currency] == 0.0) {
            balances.remove(currency)
        }

        balancesManager.updateBalances(balances)
    }

    override fun getBalances(): StateFlow<Map<String, Double>> = balancesManager.balancesFlow
}
