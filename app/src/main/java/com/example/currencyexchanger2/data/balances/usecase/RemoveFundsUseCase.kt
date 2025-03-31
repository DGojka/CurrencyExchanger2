package com.example.currencyexchanger2.data.balances.usecase

import com.example.currencyexchanger2.data.transactions.CurrencyAmount

interface RemoveFundsUseCase {
    operator fun invoke(currencyAmount: CurrencyAmount)
}
