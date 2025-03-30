package com.example.currencyexchanger2.di

import com.example.currencyexchanger2.network.ExchangeRatesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCurrencyExchangeClient(retrofit: Retrofit): ExchangeRatesClient = retrofit.create(ExchangeRatesClient::class.java)

    companion object {
        private const val API_BASE_URL = "https://developers.paysera.com/tasks/api/"
    }
}
