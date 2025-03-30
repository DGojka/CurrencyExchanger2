package com.example.currencyexchanger2.currencyexchangescreen

interface ExchangeCalculator {
    fun calculateExchange(
        amount: Double,
        currencyFrom: String,
        currencyTo: String,
    )
}
