package com.example.currencyexchanger2.currencyexchangescreen.managers.balances

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesBalancesStorage(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson = Gson(),
) : BalancesStorage {
    override fun loadBalances(): Map<String, Double> {
        val json = sharedPreferences.getString(BALANCES_KEY, null)
        return if (json.isNullOrEmpty()) {
            emptyMap()
        } else {
            val type = object : TypeToken<Map<String, Double>>() {}.type
            gson.fromJson(json, type)
        }
    }

    override fun saveBalances(balances: Map<String, Double>) {
        val json = gson.toJson(balances)
        sharedPreferences.edit { putString(BALANCES_KEY, json) }
    }

    companion object {
        private const val BALANCES_PREFS = "BALANCES_PREFS"
        private const val BALANCES_KEY = "BALANCES_KEY"
    }
}
