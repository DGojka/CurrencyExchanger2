package com.example.currencyexchanger2.currencyexchangescreen

data class ExchangeScreenUiState(
    val balances: List<String> = emptyList(),
    val amountToSell: Double = 0.0,
    val currencyToSell: String = "EUR",
    val convertedAmount: Double? = null,
    val currencyToReceive: String = "USD",
    val availableCurrencies: List<String> = emptyList()
)