package com.example.currencyexchanger2.currencyexchangescreen.managers

import com.example.currencyexchanger2.currencyexchangescreen.data.ExchangeResult
import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProvider
import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.ManageFundsUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.TransactionUseCase
import com.example.currencyexchanger2.formatTo2Decimals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExecuteExchangeUseCase @Inject constructor(
    private val manageFundsUseCase: ManageFundsUseCase,
    private val feeProvider: FeeProvider,
    private val transactionUseCase: TransactionUseCase,
) {
    operator fun invoke(
        from: CurrencyAmount,
        to: CurrencyAmount,
    ): Flow<ExchangeResult> =
        flow {
            try {
                manageFundsUseCase.removeFunds(currencyAmount = from)
                manageFundsUseCase.addFunds(currencyAmount = to)
                transactionUseCase.incrementTransactionCount()
                emit(
                    ExchangeResult.Success(
                        "You have converted ${from.amount} ${from.currency} to ${to.amount} ${to.currency}. Commission Fee: ${
                            feeProvider.getFee(
                                from.amount
                            ).formatTo2Decimals()
                        } ${from.currency}.",
                    ),
                )
            } catch (e: Exception) {
                emit(ExchangeResult.Error("Transaction failed: ${e.message}"))
            }
        }.flowOn(Dispatchers.IO)
}
