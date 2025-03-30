package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.currencyexchangescreen.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.ExchangeCalculatorImpl
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
    fun provideExchangeCalculator(): ExchangeCalculator = ExchangeCalculatorImpl()
}
