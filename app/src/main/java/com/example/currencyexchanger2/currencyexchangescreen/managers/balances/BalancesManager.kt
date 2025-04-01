package com.example.currencyexchanger2.currencyexchangescreen.managers.balances

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BalancesManager @Inject constructor(
    private val storage: BalancesStorage,
) {
    private val _balancesFlow = MutableStateFlow(storage.loadBalances())
    val balancesFlow: StateFlow<Map<String, Double>> get() = _balancesFlow

    fun updateBalances(newBalances: Map<String, Double>) {
        storage.saveBalances(newBalances)
        _balancesFlow.value = newBalances
    }
}
