package com.example.currencyexchanger2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.currencyexchanger2.currencyexchangescreen.ui.CurrencyExchangeScreen
import com.example.currencyexchanger2.managers.InitialBalanceManager
import com.example.currencyexchanger2.ui.theme.CurrencyExchanger2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var initialBalanceManager: InitialBalanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBalanceManager.initializeBalanceIfNeeded()
        enableEdgeToEdge()
        setContent {
            CurrencyExchanger2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar(stringResource(id = R.string.top_bar_title)) },
                ) { innerPadding ->
                    CurrencyExchangeScreen(innerPadding)
                }
            }
        }
    }
}
