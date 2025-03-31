package com.example.currencyexchanger2.managers

interface FirstRunManager {
    fun isFirstRun(): Boolean

    fun setFirstRunCompleted()
}
