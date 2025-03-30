package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapper
import com.example.currencyexchanger2.network.mappers.ExchangeRatesMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    @Singleton
    fun provideExchangeRatesMapper(): ExchangeRatesMapper = ExchangeRatesMapperImpl()
}
