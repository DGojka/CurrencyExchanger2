package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.balances.BalancesRepository
import kotlinx.coroutines.flow.StateFlow

class GetBalancesUseCaseImpl(
    private val repository: BalancesRepository,
) : GetBalancesUseCase {
    override fun invoke(): StateFlow<Map<String, Double>> = repository.getBalances()
}
