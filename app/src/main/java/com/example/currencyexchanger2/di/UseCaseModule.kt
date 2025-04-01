package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepository
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.AddFundsUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.AddFundsUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.GetBalancesUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.GetBalancesUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.RemoveFundsUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.RemoveFundsUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.GetTransactionCountUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.GetTransactionCountUseCaseImpl
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.IncrementTransactionCountUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.IncrementTransactionCountUseCaseImpl
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
    fun provideGetTransactionCountUseCase(transactionCountRepository: TransactionCountRepository): GetTransactionCountUseCase =
        GetTransactionCountUseCaseImpl(transactionCountRepository)

    @Provides
    @Singleton
    fun provideIncrementTransactionCountUseCase(transactionCountRepository: TransactionCountRepository): IncrementTransactionCountUseCase =
        IncrementTransactionCountUseCaseImpl(transactionCountRepository)

    @Provides
    @Singleton
    fun provideAddFundsUseCase(repository: BalancesRepository): AddFundsUseCase = AddFundsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideRemoveFundsUseCase(repository: BalancesRepository): RemoveFundsUseCase = RemoveFundsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetBalancesUseCase(repository: BalancesRepository): GetBalancesUseCase = GetBalancesUseCaseImpl(repository)
}
