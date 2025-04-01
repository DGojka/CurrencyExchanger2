package com.example.currencyexchanger2.currencyexchangescreen.managers

import com.example.currencyexchanger2.currencyexchangescreen.helpers.FeeProvider
import com.example.currencyexchanger2.data.balances.usecase.AddFundsUseCase
import com.example.currencyexchanger2.data.balances.usecase.RemoveFundsUseCase
import com.example.currencyexchanger2.data.transactions.CurrencyAmount
import com.example.currencyexchanger2.formatTo2Decimals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProceedExchangeUseCase @Inject constructor(
    private val addFundsUseCase: AddFundsUseCase,
    private val removeFundsUseCase: RemoveFundsUseCase,
    private val feeProvider: FeeProvider
) {
    operator fun invoke(
        from: CurrencyAmount,
        to: CurrencyAmount,
    ): Flow<ExchangeResult> =
        flow {
            try {
                removeFundsUseCase(currencyAmount = from)
                addFundsUseCase(currencyAmount = to)
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
