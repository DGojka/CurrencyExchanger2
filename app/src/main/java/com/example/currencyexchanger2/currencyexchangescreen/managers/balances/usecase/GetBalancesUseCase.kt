package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import kotlinx.coroutines.flow.StateFlow

interface GetBalancesUseCase {
    operator fun invoke(): StateFlow<Map<String, Double>>
}
