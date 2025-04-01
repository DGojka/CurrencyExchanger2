package com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates

import com.example.currencyexchanger2.network.data.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesProvider {
    val exchangeRatesFlow: Flow<ExchangeRates>
}
