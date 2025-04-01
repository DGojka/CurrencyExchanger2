package com.example.currencyexchanger2.currencyexchangescreen.helpers

import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.TransactionUseCase

class FeeProviderImpl(
    private val transactionUseCase: TransactionUseCase
) : FeeProvider {
    override fun getFee(amount: Double): Double =
        if (transactionUseCase.getTransactionCount() < FREE_TRANSACTIONS_LIMIT) 0.0 else amount * FEE_PERCENTAGE

    companion object {
        private const val FREE_TRANSACTIONS_LIMIT = 5
        private const val FEE_PERCENTAGE = 0.007 // 0.7%
    }
}
