package com.example.currencyexchanger2.network

import com.example.currencyexchanger2.network.data.ExchangeRatesJson
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeRatesClient {
    @GET("currency-exchange-rates")
    suspend fun getExchangeRates(): Response<ExchangeRatesJson>
}
