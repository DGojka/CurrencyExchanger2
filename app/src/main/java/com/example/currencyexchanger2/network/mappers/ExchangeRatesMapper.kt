package com.example.currencyexchanger2.network.mappers

import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.data.ExchangeRatesJson

interface ExchangeRatesMapper {
    fun map(exchangeRatesJson: ExchangeRatesJson): ExchangeRates
}
