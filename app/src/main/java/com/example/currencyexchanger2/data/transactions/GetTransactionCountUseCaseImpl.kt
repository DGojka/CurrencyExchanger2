package com.example.currencyexchanger2.data.transactions

import javax.inject.Inject

class GetTransactionCountUseCaseImpl @Inject constructor(
    private val transactionCountRepository: TransactionCountRepository
) : GetTransactionCountUseCase {
    override fun invoke(): Int = transactionCountRepository.getTransactionCount()
}