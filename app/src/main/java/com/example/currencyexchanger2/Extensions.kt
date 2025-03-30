package com.example.currencyexchanger2

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.Locale

fun <T> inFlow(block: suspend () -> Response<T>): Flow<Response<T>> = flow { emit(block()) }

fun Double.formatTo2Decimals() = String.format(Locale.US, "%.2f", this)
