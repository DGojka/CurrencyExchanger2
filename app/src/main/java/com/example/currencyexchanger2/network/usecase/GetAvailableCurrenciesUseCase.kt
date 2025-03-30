package com.example.currencyexchanger2.network.usecase

interface GetAvailableCurrenciesUseCase {
    suspend operator fun invoke(): List<String>
}
