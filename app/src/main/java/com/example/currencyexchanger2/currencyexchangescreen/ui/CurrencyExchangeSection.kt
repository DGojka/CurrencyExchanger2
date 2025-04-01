package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.currencyexchanger2.R

@Composable
fun CurrencyExchangeSection(
    currencyList: List<String>,
    selectedCurrencyToSell: String,
    selectedCurrencyToReceive: String,
    calculatedExchangeAmount: String,
    onAmountChange: (newAmount: String) -> Unit,
    onCurrencyToSellChange: (newCurrency: String) -> Unit,
    onCurrencyToReceiveChange: (newCurrency: String) -> Unit,
) {
    Text(text = stringResource(id = R.string.currency_exchange))

    SellSection(onAmountChange, currencyList, selectedCurrencyToSell, onCurrencyToSellChange)
    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    ReceiveSection(
        calculatedExchangeAmount,
        currencyList,
        selectedCurrencyToReceive,
        onCurrencyToReceiveChange,
    )
}

@Composable
private fun SellSection(
    onAmountChange: (newAmount: String) -> Unit,
    currencyList: List<String>,
    selectedCurrencyToSell: String,
    onCurrencyToSellChange: (newCurrency: String) -> Unit,
) {
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
        AmountTextField(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
        ) { onAmountChange(it) }
        Spinner(currencyList, selectedCurrencyToSell) { onCurrencyToSellChange(it) }
    }
}

@Composable
private fun ReceiveSection(
    calculatedExchangeAmount: String,
    currencyList: List<String>,
    selectedCurrencyToReceive: String,
    onCurrencyToReceiveChange: (newCurrency: String) -> Unit,
) {
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
        Spinner(currencyList, selectedCurrencyToReceive) { onCurrencyToReceiveChange(it) }
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
