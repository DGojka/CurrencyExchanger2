package com.example.currencyexchanger2.data.balances.usecase

import kotlinx.coroutines.flow.StateFlow

interface GetBalancesUseCase {
    operator fun invoke(): StateFlow<Map<String, Double>>
}
