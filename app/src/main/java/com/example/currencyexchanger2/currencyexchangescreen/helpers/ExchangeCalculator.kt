package com.example.currencyexchanger2.currencyexchangescreen.helpers

import kotlinx.coroutines.flow.Flow

interface ExchangeCalculator {
    fun calculateExchange(
        amount: Double,
        currencyFrom: String,
        currencyTo: String,
    ): Flow<Double>
}
