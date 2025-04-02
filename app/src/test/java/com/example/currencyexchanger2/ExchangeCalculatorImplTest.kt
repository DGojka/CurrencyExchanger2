package com.example.currencyexchanger2

import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculatorImpl
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProvider
import com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates.ExchangeRatesProvider
import com.example.currencyexchanger2.network.data.ExchangeRates
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExchangeCalculatorImplTest {
    private lateinit var exchangeRatesProvider: ExchangeRatesProvider
    private lateinit var feeProvider: FeeProvider
    private lateinit var exchangeCalculator: ExchangeCalculator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        exchangeRatesProvider = mockk()
        feeProvider = mockk()
        exchangeCalculator = ExchangeCalculatorImpl(exchangeRatesProvider, feeProvider)
    }

    @Test
    fun `calculateExchange should return correct converted amount`() = runBlocking {
        val exchangeRates = ExchangeRates(
            base = "EUR",
            rates = mapOf("USD" to 1.2, "GBP" to 0.75)
        )
        every { exchangeRatesProvider.exchangeRatesFlow() } returns flowOf(exchangeRates)
        every { feeProvider.getFee(100.0) } returns 2.0

        val resultFlow: Flow<Double> = exchangeCalculator.calculateExchange(100.0, "EUR", "USD")
        val result = resultFlow.first()

         // (100 - 2) * 1.2 = 117.6
        assertEquals(117.6, result, 0.01)
    }

    @Test
    fun `calculateExchange should return correct converted amount for USD to PLN when USD is not base currency`() = runBlocking {
        val exchangeRates = ExchangeRates(
            base = "EUR",
            rates = mapOf("USD" to 1.2, "PLN" to 4.5)
        )
        every { exchangeRatesProvider.exchangeRatesFlow() } returns flowOf(exchangeRates)
        every { feeProvider.getFee(100.0) } returns 2.0

        val resultFlow: Flow<Double> = exchangeCalculator.calculateExchange(100.0, "USD", "PLN")
        val result = resultFlow.first()

        // (100 - 2) * (1/1.2) * 4.5 = 367.5
        assertEquals(367.5, result, 0.01)
    }


    @Test(expected = RuntimeException::class)
    fun `calculateExchange should throw error if rate missing`(): Unit = runBlocking {
        val exchangeRates = ExchangeRates(base = "USD", rates = mapOf("EUR" to 0.85))
        every { exchangeRatesProvider.exchangeRatesFlow() } returns flowOf(exchangeRates)
        every { feeProvider.getFee(100.0) } returns 2.0

        exchangeCalculator.calculateExchange(100.0, "USD", "GBP").first()
    }
}
