package com.example.currencyexchanger2.network.data

data class ExchangeRatesJson(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
