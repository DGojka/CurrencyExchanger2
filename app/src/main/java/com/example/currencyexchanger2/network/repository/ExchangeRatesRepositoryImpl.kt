package com.example.currencyexchanger2.network.repository

import android.accounts.NetworkErrorException
import com.example.currencyexchanger2.inFlow
import com.example.currencyexchanger2.network.ExchangeRatesClient
import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ExchangeRatesRepositoryImpl(
    private val exchangeRatesMapper: ExchangeRatesMapper,
    private val client: ExchangeRatesClient,
) : ExchangeRatesRepository {
    override suspend fun getExchangeRates(): ExchangeRates =
        inFlow { client.getExchangeRates() }
            .map {
                it.body() ?: throw NetworkErrorException()
            }.map { exchangeRatesMapper.map(it) }
            .first()
}
