package com.example.currencyexchanger2.managers

import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.AddFundsUseCase
import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import javax.inject.Inject

class InitialBalanceManager @Inject constructor(
    private val addFundsUseCase: AddFundsUseCase,
    private val firstRunManager: FirstRunManager,
) {
    fun initializeBalanceIfNeeded() {
        if (firstRunManager.isFirstRun()) {
            addFundsUseCase(currencyAmount = CurrencyAmount(1000.0, "EUR"))
            firstRunManager.setFirstRunCompleted()
        }
    }
}
