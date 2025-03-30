package com.example.currencyexchanger2.network.usecase

import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.repository.ExchangeRatesRepository

class GetExchangeRatesUseCaseImpl(
    private val exchangeRatesRepository: ExchangeRatesRepository,
) : GetExchangeRatesUseCase {
    override suspend fun invoke(): ExchangeRates = exchangeRatesRepository.fetchExchangeRates()
}
