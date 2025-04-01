package com.example.currencyexchanger2.currencyexchangescreen.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionCountRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : TransactionCountRepository {
    override fun getTransactionCount(): Long = sharedPreferences.getLong(TRANSACTION_COUNT_KEY, 0L)

    override fun incrementTransactionCount() {
        val currentCount = sharedPreferences.getLong(TRANSACTION_COUNT_KEY, 0)
        sharedPreferences.edit {
            putLong(TRANSACTION_COUNT_KEY, currentCount + 1L)
        }
    }

    companion object {
        private const val TRANSACTION_COUNT_KEY = "TRANSACTION_COUNT"
    }
}
