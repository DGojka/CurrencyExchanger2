package com.example.currencyexchanger2.currencyexchangescreen.managers.transactions

interface TransactionUseCase {
    fun getTransactionCount() : Long

    fun incrementTransactionCount()
}