package com.example.currencyexchanger2.managers

import android.content.SharedPreferences
import androidx.core.content.edit

class FirstRunManagerImpl(
    private val sharedPreferences: SharedPreferences,
) : FirstRunManager {
    override fun isFirstRun(): Boolean = sharedPreferences.getBoolean(FIRST_RUN_KEY, true)

    override fun setFirstRunCompleted() {
        sharedPreferences.edit { putBoolean(FIRST_RUN_KEY, false) }
    }

    companion object {
        private const val FIRST_RUN_KEY = "isFirstRun"
    }
}
