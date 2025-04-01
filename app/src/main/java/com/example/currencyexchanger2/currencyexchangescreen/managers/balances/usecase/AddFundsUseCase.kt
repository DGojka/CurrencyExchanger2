package com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase

import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount

interface AddFundsUseCase {
    operator fun invoke(currencyAmount: CurrencyAmount)
}
