package com.example.currencyexchanger2.currencyexchangescreen.data

sealed class ExchangeResult(
    open val message: String,
) {
    data class Success(
        override val message: String,
        val from: CurrencyAmount,
        val to: CurrencyAmount,
        val fee: CurrencyAmount,
    ) : ExchangeResult(message)

    data class Error(
        override val message: String,
    ) : ExchangeResult(message)
}
