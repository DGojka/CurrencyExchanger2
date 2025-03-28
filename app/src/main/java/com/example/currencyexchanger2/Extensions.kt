package com.example.currencyexchanger2

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> inFlow(block: suspend () -> Response<T>): Flow<Response<T>> {
    return flow { emit(block()) }
}
