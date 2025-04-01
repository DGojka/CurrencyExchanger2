package com.example.currencyexchanger2.currencyexchangescreen.managers.transactions

import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepository

class TransactionUseCaseImpl(private val transactionCountRepository: TransactionCountRepository) :
    TransactionUseCase {
    override fun getTransactionCount(): Long = transactionCountRepository.getTransactionCount()

    override fun incrementTransactionCount() =
        transactionCountRepository.incrementTransactionCount()
}