package com.example.currencyexchanger2.network.repository

import com.example.currencyexchanger2.network.data.ExchangeRates

interface ExchangeRatesRepository {
    suspend fun getExchangeRates(): ExchangeRates
}
