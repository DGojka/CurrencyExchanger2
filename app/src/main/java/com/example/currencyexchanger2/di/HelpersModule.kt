package com.example.currencyexchanger2.di

import android.content.SharedPreferences
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculatorImpl
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProvider
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProviderImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates.ExchangeRatesProvider
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.TransactionUseCase
import com.example.currencyexchanger2.managers.FirstRunManager
import com.example.currencyexchanger2.managers.FirstRunManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HelpersModule {
    @Provides
    @Singleton
    fun provideFeeProvider(transactionUseCase: TransactionUseCase): FeeProvider =
        FeeProviderImpl(transactionUseCase)

    @Provides
    @Singleton
    fun provideExchangeCalculator(
        exchangeRatesProvider: ExchangeRatesProvider,
        feeProvider: FeeProvider,
    ): ExchangeCalculator =
        ExchangeCalculatorImpl(
            exchangeRatesProvider = exchangeRatesProvider,
            feeProvider = feeProvider,
        )

    @Provides
    @Singleton
    fun provideFirstRunManager(sharedPreferences: SharedPreferences): FirstRunManager = FirstRunManagerImpl(sharedPreferences)
}
