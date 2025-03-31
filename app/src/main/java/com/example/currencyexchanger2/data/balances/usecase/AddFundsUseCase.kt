package com.example.currencyexchanger2.data.balances.usecase

interface AddFundsUseCase {
    operator fun invoke(
        currency: String,
        amount: Double,
    )
}
