package com.example.currencyexchanger2.currencyexchangescreen.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionCountRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : TransactionCountRepository {
    override fun getTransactionCount(): Int = sharedPreferences.getInt(TRANSACTION_COUNT_KEY, 0)

    override fun incrementTransactionCount() {
        val currentCount = sharedPreferences.getInt(TRANSACTION_COUNT_KEY, 0)
        sharedPreferences.edit {
            putInt(TRANSACTION_COUNT_KEY, currentCount + 1)
        }
    }

    companion object {
        private const val TRANSACTION_COUNT_KEY = "TRANSACTION_COUNT"
    }
}
