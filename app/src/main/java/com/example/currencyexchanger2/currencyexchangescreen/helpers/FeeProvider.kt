package com.example.currencyexchanger2.currencyexchangescreen.helpers

interface FeeProvider {
    fun getFee(amount: Double): Double
}
