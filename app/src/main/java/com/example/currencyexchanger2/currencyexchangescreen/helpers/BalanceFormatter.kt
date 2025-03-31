package com.example.currencyexchanger2.currencyexchangescreen.helpers

import com.example.currencyexchanger2.formatTo2Decimals
import javax.inject.Inject

class BalanceFormatter @Inject constructor() {
    fun mapBalancesToDisplayList(balances: Map<String, Double>) =
        balances.entries.map {
            "${it.value.formatTo2Decimals()} ${it.key}"
        }
}
