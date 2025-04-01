package com.example.currencyexchanger2.currencyexchangescreen.managers.transactions

import com.example.currencyexchanger2.currencyexchangescreen.repository.TransactionCountRepository
import javax.inject.Inject

class GetTransactionCountUseCaseImpl @Inject constructor(
    private val transactionCountRepository: TransactionCountRepository
) : GetTransactionCountUseCase {
    override fun invoke(): Int = transactionCountRepository.getTransactionCount()
}