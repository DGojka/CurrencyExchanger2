package com.example.currencyexchanger2.currencyexchangescreen.managers.balances

interface BalancesStorage {
    fun loadBalances(): Map<String, Double>

    fun saveBalances(balances: Map<String, Double>)
}
