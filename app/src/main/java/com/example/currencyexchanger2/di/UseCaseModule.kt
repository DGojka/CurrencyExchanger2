package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.network.repository.ExchangeRatesRepository
import com.example.currencyexchanger2.network.usecase.GetExchangeRatesUseCase
import com.example.currencyexchanger2.network.usecase.GetExchangeRatesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetExchangeRatesUseCase(exchangeRatesRepository: ExchangeRatesRepository): GetExchangeRatesUseCase =
        GetExchangeRatesUseCaseImpl(exchangeRatesRepository)
}
