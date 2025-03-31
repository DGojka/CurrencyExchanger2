package com.example.currencyexchanger2.currencyexchangescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.data.balances.BalancesRepository
import com.example.currencyexchanger2.data.balances.usecase.AddFundsUseCase
import com.example.currencyexchanger2.data.balances.usecase.GetBalancesUseCase
import com.example.currencyexchanger2.data.balances.usecase.RemoveFundsUseCase
import com.example.currencyexchanger2.formatTo2Decimals
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val exchangeCalculator: ExchangeCalculator,
    private val getAvailableCurrenciesUseCase: GetAvailableCurrenciesUseCase,
    private val addFundsUseCase: AddFundsUseCase,
    private val removeFundsUseCase: RemoveFundsUseCase,
    private val getBalancesUseCase: GetBalancesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExchangeScreenUiState())
    val uiState: StateFlow<ExchangeScreenUiState> = _uiState.asStateFlow()

    init {
        loadBalances()
        loadAvailableCurrencies()
    }

    fun updateAmount(amount: String?) {
        val amountToSet = amount?.toDoubleOrNull() ?: 0.0
        _uiState.update { it.copy(amountToSell = amountToSet) }
        recalculateExchange()
    }

    fun updateCurrencyToSell(currency: String) {
        _uiState.update { it.copy(currencyToSell = currency) }
        recalculateExchange()
    }

    fun updateCurrencyToReceive(currency: String) {
        _uiState.update { it.copy(currencyToReceive = currency) }
        recalculateExchange()
    }

    private fun recalculateExchange() {
        val state = _uiState.value
        viewModelScope.launch {
            val convertedAmount =
                exchangeCalculator
                    .calculateExchange(
                        amount = state.amountToSell,
                        currencyFrom = state.currencyToSell,
                        currencyTo = state.currencyToReceive,
                    ).first()
            _uiState.update { it.copy(convertedAmount = convertedAmount.formatTo2Decimals()) }
        }
    }

    private fun loadBalances() {
        viewModelScope.launch {
            getBalancesUseCase().collect { balances ->
                _uiState.update { it.copy(balances = balances.mapBalancesToUi()) }
            }
        }
    }

    private fun loadAvailableCurrencies() {
        viewModelScope.launch {
            val currencies = getAvailableCurrenciesUseCase()
            _uiState.update { it.copy(availableCurrencies = currencies) }
        }
    }

    private fun Map<String, Double>.mapBalancesToUi() =
        this.entries.map {
            "${it.value.formatTo2Decimals()} ${it.key}"
        }
}
