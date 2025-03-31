package com.example.currencyexchanger2.currencyexchangescreen.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger2.R

@Composable
fun SubmitButton(onSubmitClick: () -> Unit) {
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