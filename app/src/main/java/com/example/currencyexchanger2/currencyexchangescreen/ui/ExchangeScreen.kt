package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.currencyexchanger2.currencyexchangescreen.ExchangeViewModel

@Composable
fun CurrencyExchangeScreen(
    innerPadding: PaddingValues,
    viewModel: ExchangeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CurrencyExchangeScreenContent(
            balances = state.balances,
            currencyList = state.availableCurrencies,
            onSubmitClick = { viewModel.confirmExchange() },
            onCurrencyToReceiveChange = { currency -> viewModel.updateCurrencyToReceive(currency) },
            onCurrencyToSellChange = { currency -> viewModel.updateCurrencyToSell(currency) },
            initialCurrencyToSell = state.currencyToSell,
            initialCurrencyToReceive = state.currencyToReceive,
            onAmountChange = { newAmount -> viewModel.updateAmount(newAmount) },
            calculatedExchangeAmount = (state.convertedAmount ?: 0).toString(),
        )
    }
}

@Composable
private fun CurrencyExchangeScreenContent(
    balances: List<String> = emptyList(),
    currencyList: List<String> = emptyList(),
    initialCurrencyToSell: String = "EUR",
    initialCurrencyToReceive: String = "USD",
    calculatedExchangeAmount: String,
    onSubmitClick: () -> Unit = { },
    onCurrencyToReceiveChange: (newCurrency: String) -> Unit = { },
    onCurrencyToSellChange: (newCurrency: String) -> Unit = { },
    onAmountChange: (newAmount: String?) -> Unit = { },
) {
    BalancesSection(balances)
    CurrencyExchangeSection(
        currencyList = currencyList,
        selectedCurrencyToSell = initialCurrencyToSell,
        selectedCurrencyToReceive = initialCurrencyToReceive,
        onAmountChange = onAmountChange,
        calculatedExchangeAmount = calculatedExchangeAmount,
        onCurrencyToSellChange = onCurrencyToSellChange,
        onCurrencyToReceiveChange = onCurrencyToReceiveChange,
    )
    SubmitButton(onSubmitClick)
}