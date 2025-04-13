package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger2.R
import com.example.currencyexchanger2.currencyexchangescreen.data.ExchangeResult
import com.example.currencyexchanger2.formatTo2Decimals

@Composable
fun ExchangeResultDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .shadow(elevation = 12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description)
            HorizontalDivider(Modifier.padding(vertical = 16.dp))
            Button(onClick = onDismiss) {
                Text("Done")
            }
        }
    }
}

@Composable
fun HandleExchangeResultDialog(
    exchangeResult: ExchangeResult,
    onDismiss: () -> Unit,
) {
    val title =
        when (exchangeResult) {
            is ExchangeResult.Error -> stringResource(id = R.string.conversion_failed)
            is ExchangeResult.Success -> stringResource(id = R.string.currency_converted)
        }

    val description = when (exchangeResult) {
        is ExchangeResult.Error -> stringResource(
            id = R.string.exchange_failed_message,
            exchangeResult.message
        )
        is ExchangeResult.Success -> stringResource(
            id = R.string.exchange_success_message,
            exchangeResult.from.amount.formatTo2Decimals(),
            exchangeResult.from.currency,
            exchangeResult.to.amount.formatTo2Decimals(),
            exchangeResult.to.currency,
            exchangeResult.fee.amount.formatTo2Decimals(),
            exchangeResult.fee.currency,
        )
    }

    ExchangeResultDialog(
        title = title,
        description = description,
        onDismiss = onDismiss,
    )
}

@Composable
@Preview
fun ExchangeResultDialogPreview() {
    ExchangeResultDialog(
        "Currency Converted",
        "You have converted 100.0 EUR to 110.0 USD. Commission Fee: 0.70 EUR"
    ) { }
}