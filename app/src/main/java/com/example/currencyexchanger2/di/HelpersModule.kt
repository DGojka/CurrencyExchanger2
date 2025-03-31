package com.example.currencyexchanger2.di

import android.content.SharedPreferences
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculatorImpl
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProvider
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProviderImpl
import com.example.currencyexchanger2.data.exchangerates.ExchangeRatesProvider
import com.example.currencyexchanger2.data.transactions.GetTransactionCountUseCase
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
    fun provideFeeProvider(getTransactionCountUseCase: GetTransactionCountUseCase): FeeProvider =
        FeeProviderImpl(getTransactionCountUseCase)

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
