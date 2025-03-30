package com.example.currencyexchanger2.currencyexchangescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
        CurrencyExchangeScreenContent(state.balances)
    }
}

@Composable
private fun CurrencyExchangeScreenContent(
    balances: List<String>,
    onSubmitClick: () -> Unit = {},
    temp: () -> Unit = {},
) {
    BalancesSection(balances)
    CurrencyExchangeSection(temp)
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
        items(balances) { balace ->
            BalanceItem(balace)
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
private fun CurrencyExchangeSection(temp: () -> Unit) {
    Text(text = stringResource(id = R.string.currency_exchange))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { temp() },
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
        TextField(
            value = "100.00", // Replace with actual state management
            onValueChange = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
        )
        SpinnerComponent()
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
            text = "+110.30",
            color = Color.Green,
            modifier = Modifier.weight(1f),
        )
        SpinnerComponent()
    }
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
    // Placeholder for a dropdown/spinner implementation
    Box(
        modifier =
            Modifier
                .size(56.dp)
                .background(Color.LightGray),
    ) {
        Text(text = "▼", modifier = Modifier.align(Alignment.Center))
    }
}
