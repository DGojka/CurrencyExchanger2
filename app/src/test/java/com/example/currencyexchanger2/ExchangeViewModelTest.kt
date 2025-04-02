package com.example.currencyexchanger2

import com.example.currencyexchanger2.currencyexchangescreen.ExchangeViewModel
import com.example.currencyexchanger2.currencyexchangescreen.helpers.BalanceFormatter
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.managers.ExecuteExchangeUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.ManageFundsUseCase
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCase
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExchangeViewModelTest {

    private lateinit var exchangeCalculator: ExchangeCalculator
    private lateinit var getAvailableCurrenciesUseCase: GetAvailableCurrenciesUseCase
    private lateinit var manageFundsUseCase: ManageFundsUseCase
    private lateinit var balanceFormatter: BalanceFormatter
    private lateinit var executeExchangeUseCase: ExecuteExchangeUseCase
    private lateinit var exchangeViewModel: ExchangeViewModel

    @Before
    fun setUp() {
        exchangeCalculator = mockk()
        getAvailableCurrenciesUseCase = mockk()
        manageFundsUseCase = mockk()
        balanceFormatter = mockk()
        executeExchangeUseCase = mockk()

        exchangeViewModel = ExchangeViewModel(
            exchangeCalculator = exchangeCalculator,
            getAvailableCurrenciesUseCase = getAvailableCurrenciesUseCase,
            manageFundsUseCase = manageFundsUseCase,
            balanceFormatter = balanceFormatter,
            executeExchangeUseCase = executeExchangeUseCase
        )
    }

    @Test
    fun `updateAmount should update the amountToSell in uiState`() {
        val amount = "100.0"
        exchangeViewModel.updateAmount(amount)
        assertEquals(100.0, exchangeViewModel.uiState.value.amountToSell, 0.0)
    }

    @Test
    fun `updateCurrencyToSell should update the currencyToSell in uiState`() {
        val currency = "USD"
        exchangeViewModel.updateCurrencyToSell(currency)
        assertEquals(currency, exchangeViewModel.uiState.value.currencyToSell)
    }

    @Test
    fun `updateCurrencyToReceive should update the currencyToReceive in uiState`() {
        val currency = "EUR"
        exchangeViewModel.updateCurrencyToReceive(currency)
        assertEquals(currency, exchangeViewModel.uiState.value.currencyToReceive)
    }
}
