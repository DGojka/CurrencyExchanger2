package com.example.currencyexchanger2.data

import com.example.currencyexchanger2.network.data.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesProvider {
    val exchangeRatesFlow: Flow<ExchangeRates>
}
