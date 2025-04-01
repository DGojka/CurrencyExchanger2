package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount

interface RemoveFundsUseCase {
    operator fun invoke(currencyAmount: CurrencyAmount)
}
