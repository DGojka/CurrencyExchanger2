package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger2.R

@Composable
fun BalancesSection(balances: List<String>) {
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
