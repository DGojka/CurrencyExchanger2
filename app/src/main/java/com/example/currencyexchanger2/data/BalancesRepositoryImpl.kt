package com.example.currencyexchanger2.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BalancesRepositoryImpl(
    context: Context,
) : BalancesRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(BALANCES_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun addFunds(
        currency: String,
        amount: Double,
    ) {
        require(amount > 0) { "Receive amount must be positive." }

        val balances = getBalances().toMutableMap()
        balances[currency] = (balances[currency] ?: 0.0) + amount

        saveBalance(balances)
    }

    override fun removeFunds(
        currency: String,
        amount: Double,
    ) {
        val funds = getBalances().toMutableMap()
        val currentAmount = funds[currency] ?: 0.0

        require(amount > 0) { "Sell amount must be positive." }
        require(currentAmount > amount) { "Insufficient balance for currency: $currency." }

        funds[currency] = currentAmount - amount

        if (funds[currency] == 0.0) {
            funds.remove(currency)
        }

        saveBalance(funds)
    }

    override fun getBalances(): Map<String, Double> {
        val json = sharedPreferences.getString(BALANCES, null)
        return if (json.isNullOrEmpty()) {
            emptyMap()
        } else {
            val type = object : TypeToken<Map<String, Double>>() {}.type
            gson.fromJson(json, type)
        }
    }

    private fun saveBalance(holdings: Map<String, Double>) {
        val json = gson.toJson(holdings)
        sharedPreferences.edit { putString(BALANCES, json) }
    }

    companion object {
        private const val BALANCES_PREFS = "BalancesPrefs"
        private const val BALANCES = "balances"
    }
}
