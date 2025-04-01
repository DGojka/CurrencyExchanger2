package com.example.currencyexchanger2

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF008080),
            ),
    )
}
