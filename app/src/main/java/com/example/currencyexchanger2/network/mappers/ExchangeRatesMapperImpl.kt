package com.example.currencyexchanger2.network.mappers

import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.data.ExchangeRatesJson
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper

class ExchangeRatesMapperImpl : ExchangeRatesMapper {
    override fun map(exchangeRatesJson: ExchangeRatesJson): ExchangeRates {
        with(exchangeRatesJson) {
            return ExchangeRates(base, rates)
        }
    }
}
