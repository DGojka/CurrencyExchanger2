package com.example.currencyexchanger2.data

import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.usecase.GetExchangeRatesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExchangeRatesProviderImpl(
    val getExchangeRates: GetExchangeRatesUseCase,
) : ExchangeRatesProvider {
    override val exchangeRatesFlow: Flow<ExchangeRates> =
        flow {
            while (true) {
                emit(getExchangeRates())
                delay(5000)
            }
        }.flowOn(Dispatchers.IO)
}
