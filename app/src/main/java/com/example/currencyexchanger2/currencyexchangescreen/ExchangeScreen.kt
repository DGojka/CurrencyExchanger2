package com.example.currencyexchanger2.currencyexchangescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.currencyexchanger2.R

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
            onSubmitClick = { },
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

@Composable
private fun BalancesSection(balances: List<String>) {
    Text(text = stringResource(id = R.string.my_balances))

    BalancesList(balances)
}

@Composable
fun BalancesList(balances: List<String>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(balances) { balance ->
            BalanceItem(balance)
        }
    }
}

@Composable
fun BalanceItem(balance: String) {
    Text(
        text = balance,
        modifier =
            Modifier
                .background(Color.LightGray)
                .padding(16.dp),
    )
}

@Composable
private fun CurrencyExchangeSection(
    currencyList: List<String>,
    selectedCurrencyToSell: String,
    selectedCurrencyToReceive: String,
    calculatedExchangeAmount: String,
    onAmountChange: (newAmount: String) -> Unit,
    onCurrencyToSellChange: (newCurrency: String) -> Unit,
    onCurrencyToReceiveChange: (newCurrency: String) -> Unit,
) {
    Text(text = stringResource(id = R.string.currency_exchange))

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            tint = Color.Red,
        )
        Text(
            text = stringResource(id = R.string.sell),
            color = Color.Black,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.weight(1f))
        AmountTextField(modifier = Modifier.weight(1f)) { onAmountChange(it) }
        SimpleSpinner(currencyList, selectedCurrencyToSell) { onCurrencyToSellChange(it) }
    }

    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier.rotate(180f),
        )
        Text(
            text = stringResource(id = R.string.receive),
            color = Color.Black,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = calculatedExchangeAmount,
            color = Color.Green,
            modifier = Modifier.weight(1f),
        )
        SimpleSpinner(currencyList, selectedCurrencyToReceive) { onCurrencyToReceiveChange(it) }
    }
}

@Composable
private fun AmountTextField(
    modifier: Modifier = Modifier,
    onAmountChange: (newAmount: String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onAmountChange(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
private fun SubmitButton(onSubmitClick: () -> Unit) {
    Button(
        onClick = { onSubmitClick() },
        modifier =
            Modifier
                .fillMaxWidth()
                .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008080)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(text = stringResource(id = R.string.submit))
    }
}

@Composable
fun SpinnerComponent() {
    Box(
        modifier =
            Modifier
                .size(56.dp)
                .background(Color.LightGray),
    ) {
        Text(text = "▼", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun SimpleSpinner(
    currencyList: List<String>,
    selectedItem: String,
    onCurrencySelected: (selectedItem: String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    Log.e("asdd", currencyList.toString())

    Column {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedItem)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(),
        ) {
            Column(
                modifier =
                    Modifier
                        .heightIn(max = 300.dp)
                        .verticalScroll(rememberScrollState()),
            ) {
                currencyList.forEach { currency ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onCurrencySelected(currency)
                        },
                        text = { Text(text = currency) },
                    )
                }
            }
        }
    }
}
