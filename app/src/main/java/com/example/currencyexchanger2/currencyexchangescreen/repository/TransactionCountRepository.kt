package com.example.currencyexchanger2.currencyexchangescreen.repository

interface TransactionCountRepository {
    fun getTransactionCount(): Long

    fun incrementTransactionCount()
}
