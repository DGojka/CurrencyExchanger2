package com.example.currencyexchanger2.currencyexchangescreen.helpers

import com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates.ExchangeRatesProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExchangeCalculatorImpl(
    val exchangeRatesProvider: ExchangeRatesProvider,
    val feeProvider: FeeProvider,
) : ExchangeCalculator {

    override fun calculateExchange(
        amount: Double,
        currencyFrom: String,
        currencyTo: String,
    ): Flow<Double> {
        return exchangeRatesProvider.exchangeRatesFlow().map { rates ->
            if (currencyFrom == currencyTo) return@map amount
            val fee = feeProvider.getFee(amount)
            val amountAfterFee = amount - fee

            val baseCurrency = rates.base
            val rateToBase =
                if (currencyFrom == baseCurrency) {
                    1.0
                } else {
                    1 / (rates.rates[currencyFrom] ?: error("No exchange rate for $currencyFrom"))
                }
            val rateFromBase = rates.rates[currencyTo] ?: error("No exchange rate for $currencyTo")

            amountAfterFee * rateToBase * rateFromBase
        }
    }
}
