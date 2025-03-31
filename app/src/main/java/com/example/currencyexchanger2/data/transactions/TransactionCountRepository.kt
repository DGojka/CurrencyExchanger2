package com.example.currencyexchanger2.data.transactions

interface TransactionCountRepository {
    fun getTransactionCount(): Int

    fun incrementTransactionCount()
}
