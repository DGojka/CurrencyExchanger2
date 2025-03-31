package com.example.currencyexchanger2.currencyexchangescreen.managers

import com.example.currencyexchanger2.data.balances.usecase.AddFundsUseCase
import com.example.currencyexchanger2.data.balances.usecase.RemoveFundsUseCase
import com.example.currencyexchanger2.data.transactions.CurrencyAmount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProceedExchangeUseCase @Inject constructor(
    private val addFundsUseCase: AddFundsUseCase,
    private val removeFundsUseCase: RemoveFundsUseCase,
) {
    operator fun invoke(
        from: CurrencyAmount,
        to: CurrencyAmount,
    ): Flow<ExchangeResult> =
        flow {
            try {
                removeFundsUseCase(currencyAmount = from)
                addFundsUseCase(currencyAmount = to)
                emit(ExchangeResult.Success("Transaction successful: ${from.amount} ${from.currency} → ${to.amount} ${to.currency}"))
            } catch (e: Exception) {
                emit(ExchangeResult.Error("Transaction failed: ${e.message}"))
            }
        }.flowOn(Dispatchers.IO)
}
