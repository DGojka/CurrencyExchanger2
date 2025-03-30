package com.example.currencyexchanger2.network.usecase

import com.example.currencyexchanger2.network.repository.ExchangeRatesRepository

class GetAvailableCurrenciesUseCaseImpl(
    private val exchangeRatesRepository: ExchangeRatesRepository,
) : GetAvailableCurrenciesUseCase {
    override suspend fun invoke(): List<String> = exchangeRatesRepository.getAvailableCurrencies()
}
