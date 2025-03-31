package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.transactions.CurrencyAmount

interface AddFundsUseCase {
    operator fun invoke(currencyAmount: CurrencyAmount)
}
