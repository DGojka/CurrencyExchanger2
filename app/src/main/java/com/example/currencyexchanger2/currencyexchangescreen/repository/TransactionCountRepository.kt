package com.example.currencyexchanger2.currencyexchangescreen.repository

interface TransactionCountRepository {
    fun getTransactionCount(): Int

    fun incrementTransactionCount()
}
