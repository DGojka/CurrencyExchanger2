package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.ManageFundsUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.ManageFundsUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.TransactionUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.TransactionUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepository
import com.example.currencyexchanger2.network.repository.ExchangeRatesRepository
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCase
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCaseImpl
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

    @Provides
    @Singleton
    fun provideGetAvailableCurrenciesUseCase(exchangeRatesRepository: ExchangeRatesRepository): GetAvailableCurrenciesUseCase =
        GetAvailableCurrenciesUseCaseImpl(exchangeRatesRepository)

    @Provides
    @Singleton
    fun provideGetTransactionCountUseCase(transactionCountRepository: TransactionCountRepository): TransactionUseCase =
        TransactionUseCaseImpl(transactionCountRepository)

    @Provides
    @Singleton
    fun provideAddFundsUseCase(repository: BalancesRepository): ManageFundsUseCase = ManageFundsUseCaseImpl(repository)
}
