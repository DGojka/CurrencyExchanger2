package com.example.currencyexchanger2.repository

import android.util.Log
import com.example.currencyexchanger2.network.ExchangeRatesClient
import com.example.currencyexchanger2.network.data.ExchangeRates
import com.example.currencyexchanger2.network.data.ExchangeRatesJson
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper
import com.example.currencyexchanger2.network.repository.ExchangeRatesRepositoryImpl
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ExchangeRatesRepositoryImplTest {

    private lateinit var exchangeRatesMapper: ExchangeRatesMapper
    private lateinit var exchangeRatesClient: ExchangeRatesClient
    private lateinit var exchangeRatesRepository: ExchangeRatesRepositoryImpl

    @Before
    fun setUp() {
        exchangeRatesMapper = mockk()
        exchangeRatesClient = mockk()

        mockkStatic(android.util.Log::class)
        every { Log.e(any(), any()) } returns 0

        exchangeRatesRepository =
            ExchangeRatesRepositoryImpl(exchangeRatesMapper, exchangeRatesClient)
    }

    @Test
    fun `test fetchExchangeRates success`() = runBlocking {
        val exchangeRatesJson = ExchangeRatesJson(
            base = "EUR",
            rates = mapOf("USD" to 1.2, "GBP" to 0.85),
            date = "date"
        )
        val response: Response<ExchangeRatesJson> = Response.success(exchangeRatesJson)
        coEvery { exchangeRatesClient.getExchangeRates() } returns response
        val mappedExchangeRates = ExchangeRates("EUR", mapOf("USD" to 1.2, "GBP" to 0.85))
        every { exchangeRatesMapper.map(exchangeRatesJson) } returns mappedExchangeRates

        val result = exchangeRatesRepository.fetchExchangeRates()

        assertEquals(mappedExchangeRates, result)
        coVerify { exchangeRatesClient.getExchangeRates() }
        verify { exchangeRatesMapper.map(exchangeRatesJson) }
    }

    @Test
    fun `test fetchExchangeRates failure returns default value`() = runBlocking {
        val errorResponseBody = ResponseBody.create(
            null,
            "Error"
        )
        val response: Response<ExchangeRatesJson> = Response.error(500, errorResponseBody)

        coEvery { exchangeRatesClient.getExchangeRates() } returns response
        val result = exchangeRatesRepository.fetchExchangeRates()

        assertEquals("EUR", result.base)
        assertEquals(true, result.rates.isEmpty()) // Exception is caught and returns an empty map
    }
}
