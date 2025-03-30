package com.example.currencyexchanger2.currencyexchangescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyexchanger2.data.balances.BalancesRepository
import com.example.currencyexchanger2.formatTo2Decimals
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel
    @Inject
    constructor(
        private val exchangeCalculator: ExchangeCalculator,
        private val balancesRepository: BalancesRepository,
        private val getAvailableCurrenciesUseCase: GetAvailableCurrenciesUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(ExchangeScreenUiState())
        val uiState: StateFlow<ExchangeScreenUiState> = _uiState.asStateFlow()

        init {
            loadBalances()
        }

        private fun loadBalances() {
            viewModelScope.launch {
                balancesRepository.getBalances().collect { balances ->
                    _uiState.update { it.copy(balances = balances.mapBalancesToUi()) }
                }
            }
        }

        fun updateAmount(amount: Double) {
            _uiState.update { it.copy(amountToSell = amount) }
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
        /*        val state = _uiState.value
                viewModelScope.launch {
                    exchangeCalculator
                        .calculateExchange(
                            amount = state.amountToSell,
                            from = state.currencyToSell,
                            to = state.currencyToReceive,
                        ).collect { convertedAmount ->
                            _uiState.update { it.copy(convertedAmount = convertedAmount) }
                    }
                }*/
        }

        private fun Map<String, Double>.mapBalancesToUi() =
            this.entries.map {
                "${it.value.formatTo2Decimals()} ${it.key}"
            }
    }
