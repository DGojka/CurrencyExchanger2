package com.example.currencyexchanger2.network.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.currencyexchanger2.inFlow
import com.example.currencyexchanger2.network.ExchangeRatesClient
import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException

class ExchangeRatesRepositoryImpl(
    private val exchangeRatesMapper: ExchangeRatesMapper,
    private val client: ExchangeRatesClient,
) : ExchangeRatesRepository {
    override suspend fun fetchExchangeRates(): ExchangeRates =
        inFlow { client.getExchangeRates() }
            .map {
                if (!it.isSuccessful) throw HttpException(it)
                it.body() ?: throw NetworkErrorException("Response body is null")
            }.map { exchangeRatesMapper.map(it) }
            .retryWhen { cause, attempt ->
                Log.e(
                    "ExchangeRatesRepository",
                    "Error fetching exchange rates: ${cause.message}, attempt: $attempt",
                )
                delay(3000)
                attempt < 3
            }.catch { e ->
                Log.e("ExchangeRatesRepository", "Fetching exchange rates failed: ${e.message}")
                emit(ExchangeRates("EUR", emptyMap()))
            }.first()

    override suspend fun getAvailableCurrencies(): List<String> =
        try {
            fetchExchangeRates().rates.keys.toList()
        } catch (e: Exception) {
            Log.e("Exception", e.message.toString())
            emptyList()
        }
}
