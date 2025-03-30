package com.example.currencyexchanger2.data

interface BalanceRepository {
    fun addFunds(currency: String, amount: Double)
    fun removeFunds(currency: String, amount: Double)
    fun getBalances(): Map<String, Double>
}
