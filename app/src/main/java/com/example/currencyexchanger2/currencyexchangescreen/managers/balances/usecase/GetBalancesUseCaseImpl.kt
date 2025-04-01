package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import kotlinx.coroutines.flow.StateFlow

class GetBalancesUseCaseImpl(
    private val repository: BalancesRepository,
) : GetBalancesUseCase {
    override fun invoke(): StateFlow<Map<String, Double>> = repository.getBalances()
}
