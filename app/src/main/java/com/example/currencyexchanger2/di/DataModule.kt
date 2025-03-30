package com.example.currencyexchanger2.di

import android.content.Context
import com.example.currencyexchanger2.data.BalanceRepository
import com.example.currencyexchanger2.data.BalanceRepositoryImpl
import com.example.currencyexchanger2.data.ExchangeRatesProvider
import com.example.currencyexchanger2.data.ExchangeRatesProviderImpl
import com.example.currencyexchanger2.network.ExchangeRatesClient
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper
import com.example.currencyexchanger2.network.repository.ExchangeRatesRepository
import com.example.currencyexchanger2.network.repository.ExchangeRatesRepositoryImpl
import com.example.currencyexchanger2.network.usecase.GetExchangeRatesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideExchangeRatesRepository(
        exchangeRatesMapper: ExchangeRatesMapper,
        client: ExchangeRatesClient,
    ): ExchangeRatesRepository = ExchangeRatesRepositoryImpl(exchangeRatesMapper, client)

    @Provides
    @Singleton
    fun provideBalanceRepository(
        @ApplicationContext context: Context,
    ): BalanceRepository = BalanceRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideExchangeRatesProvider(getExchangeRatesUseCase: GetExchangeRatesUseCase): ExchangeRatesProvider =
        ExchangeRatesProviderImpl(getExchangeRatesUseCase)
}
