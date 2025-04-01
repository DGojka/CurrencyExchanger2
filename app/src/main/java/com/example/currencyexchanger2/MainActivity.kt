package com.example.currencyexchanger2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.currencyexchanger2.currencyexchangescreen.ui.CurrencyExchangeScreen
import com.example.currencyexchanger2.ui.theme.CurrencyExchanger2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyExchanger2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar() },
                ) { innerPadding ->
                    CurrencyExchangeScreen(innerPadding)
                }
            }
        }
    }
}
