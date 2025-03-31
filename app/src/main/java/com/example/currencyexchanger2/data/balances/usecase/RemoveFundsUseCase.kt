package com.example.currencyexchanger2.data.balances.usecase

interface RemoveFundsUseCase {
    operator fun invoke(
        currency: String,
        amount: Double,
    )
}
