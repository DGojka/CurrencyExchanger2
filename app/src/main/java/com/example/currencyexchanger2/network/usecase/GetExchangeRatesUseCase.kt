package com.example.currencyexchanger2.network.usecase

import com.example.currencyexchanger2.network.data.ExchangeRates

interface GetExchangeRatesUseCase {
    suspend operator fun invoke(): ExchangeRates
}
