package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
            currencyToSell = state.currencyToSell,
            currencyToReceive = state.currencyToReceive,
            onAmountChange = { newAmount -> viewModel.updateAmount(newAmount) },
            calculatedExchangeAmount = (state.convertedAmount ?: 0).toString(),
        )
    }
}

@Composable
private fun CurrencyExchangeScreenContent(
    currencyToSell: String,
    currencyToReceive: String,
    calculatedExchangeAmount: String,
    balances: List<String> = emptyList(),
    currencyList: List<String> = emptyList(),
    onSubmitClick: () -> Unit = { },
    onCurrencyToReceiveChange: (newCurrency: String) -> Unit = { },
    onCurrencyToSellChange: (newCurrency: String) -> Unit = { },
    onAmountChange: (newAmount: String?) -> Unit = { },
) {
    Column {
        BalancesSection(balances)
        CurrencyExchangeSection(
            currencyList = currencyList,
            selectedCurrencyToSell = currencyToSell,
            selectedCurrencyToReceive = currencyToReceive,
            onAmountChange = onAmountChange,
            calculatedExchangeAmount = calculatedExchangeAmount,
            onCurrencyToSellChange = onCurrencyToSellChange,
            onCurrencyToReceiveChange = onCurrencyToReceiveChange,
        )
        Spacer(Modifier.height(12.dp))
        SubmitButton(onSubmitClick)
    }
}

@Composable
@Preview
private fun CurrencyExchangeScreenPreview() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CurrencyExchangeScreenContent(
            balances = listOf("1000 EUR"),
            currencyToSell = "EUR",
            currencyToReceive = "USD",
            calculatedExchangeAmount = "110.00",
        )
    }
}
