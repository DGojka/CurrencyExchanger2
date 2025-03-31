package com.example.currencyexchanger2.data.transactions

import kotlinx.coroutines.flow.Flow

interface GetTransactionCountUseCase {
    operator fun invoke(): Int
}
