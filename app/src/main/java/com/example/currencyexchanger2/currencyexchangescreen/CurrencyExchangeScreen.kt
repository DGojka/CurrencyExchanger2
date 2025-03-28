package com.example.currencyexchanger2.currencyexchangescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun CurrencyExchangeScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CurrencyExchangeScreenContent()
    }
}

@Composable
private fun CurrencyExchangeScreenContent() {
    BalancesSection()
    CurrencyExchangeSection()
}

@Composable
private fun BalancesSection() {
    Text(text = stringResource(id = R.string.my_balances))

    BalancesList()
}

@Composable
fun BalancesList() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) { index ->
            BalanceItem(index)
        }
    }
}

@Composable
fun BalanceItem(index: Int) {
    Text(
        text = "Item $index",
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp)
    )
}

@Composable
private fun CurrencyExchangeSection() {

    Text(text = stringResource(id = R.string.currency_exchange))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            tint = Color.Red
        )
        Text(
            text = stringResource(id = R.string.sell),
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = "100.00", // Replace with actual state management
            onValueChange = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f)
        )
        SpinnerComponent()
    }

    Divider(color = Color.Gray, thickness = 1.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier.rotate(180f)
        )
        Text(
            text = stringResource(id = R.string.receive),
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "+110.30",
            color = Color.Green,
            modifier = Modifier.weight(1f)
        )
        SpinnerComponent()
    }

    Button(
        onClick = { /* Handle submit */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008080)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = stringResource(id = R.string.submit))
    }
}

@Composable
fun SpinnerComponent() {
    // Placeholder for a dropdown/spinner implementation
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color.LightGray)
    ) {
        Text(text = "▼", modifier = Modifier.align(Alignment.Center))
    }
}
