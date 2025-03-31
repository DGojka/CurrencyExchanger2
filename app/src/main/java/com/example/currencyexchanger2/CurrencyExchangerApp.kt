package com.example.currencyexchanger2

import android.app.Application
import com.example.currencyexchanger2.managers.InitialBalanceManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CurrencyExchangerApp : Application() {
    @Inject
    lateinit var initialBalanceManager: InitialBalanceManager

    override fun onCreate() {
        super.onCreate()

        initialBalanceManager.initializeBalanceIfNeeded()
    }
}
