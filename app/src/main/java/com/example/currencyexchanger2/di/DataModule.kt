package com.example.currencyexchanger2.di

import android.content.Context
import android.content.SharedPreferences
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.BalancesManager
import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepositoryImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.BalancesStorage
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.SharedPreferencesBalancesStorage
import com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates.ExchangeRatesProvider
import com.example.currencyexchanger2.currencyexchangescreen.managers.exchangerates.ExchangeRatesProviderImpl
import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepository
import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepositoryImpl
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
    fun provideBalancesStorage(sharedPreferences: SharedPreferences): BalancesStorage = SharedPreferencesBalancesStorage(sharedPreferences)

    @Provides
    @Singleton
    fun provideBalancesRepository(balancesManager: BalancesManager): BalancesRepository = BalancesRepositoryImpl(balancesManager)

    @Provides
    @Singleton
    fun provideExchangeRatesProvider(getExchangeRatesUseCase: GetExchangeRatesUseCase): ExchangeRatesProvider =
        ExchangeRatesProviderImpl(getExchangeRatesUseCase)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTransactionCountRepository(sharedPreferences: SharedPreferences): TransactionCountRepository =
        TransactionCountRepositoryImpl(sharedPreferences)

    companion object {
        private const val APP_PREFS = "APP_PREFS"
    }
}
