package com.example.currencyexchanger2.currencyexchangescreen.managers.transactions

import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepository
import javax.inject.Inject

class IncrementTransactionCountUseCaseImpl @Inject constructor(
    private val transactionCountRepository: TransactionCountRepository
) : IncrementTransactionCountUseCase {
    override fun invoke() {
        transactionCountRepository.incrementTransactionCount()
    }
}