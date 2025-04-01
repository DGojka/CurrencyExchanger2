package com.example.currencyexchanger2.managers

import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.ManageFundsUseCase
import javax.inject.Inject

class InitialBalanceManager @Inject constructor(
    private val manageFundsUseCase: ManageFundsUseCase,
    private val firstRunManager: FirstRunManager,
) {
    fun initializeBalanceIfNeeded() {
        if (firstRunManager.isFirstRun()) {
            manageFundsUseCase.addFunds(currencyAmount = CurrencyAmount(1000.0, "EUR"))
            firstRunManager.setFirstRunCompleted()
        }
    }
}
