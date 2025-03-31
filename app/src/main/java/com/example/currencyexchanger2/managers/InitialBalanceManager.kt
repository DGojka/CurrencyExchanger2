package com.example.currencyexchanger2.managers

import com.example.currencyexchanger2.data.balances.usecase.AddFundsUseCase
import javax.inject.Inject

class InitialBalanceManager @Inject constructor(
    private val addFundsUseCase: AddFundsUseCase,
    private val firstRunManager: FirstRunManager,
) {
    fun initializeBalanceIfNeeded() {
        if (firstRunManager.isFirstRun()) {
            addFundsUseCase("EUR", 1000.0)
            firstRunManager.setFirstRunCompleted()
        }
    }
}
