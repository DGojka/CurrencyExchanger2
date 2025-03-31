package com.example.currencyexchanger2.currencyexchangescreen.managers

sealed class ExchangeResult {
    data class Success(
        val message: String,
    ) : ExchangeResult()

    data class Error(
        val message: String,
    ) : ExchangeResult()
}
