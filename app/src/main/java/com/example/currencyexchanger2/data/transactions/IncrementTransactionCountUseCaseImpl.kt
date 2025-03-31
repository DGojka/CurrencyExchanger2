package com.example.currencyexchanger2.data.transactions

import javax.inject.Inject

class IncrementTransactionCountUseCaseImpl @Inject constructor(
    private val transactionCountRepository: TransactionCountRepository
) : IncrementTransactionCountUseCase {
    override fun invoke() {
        transactionCountRepository.incrementTransactionCount()
    }
}