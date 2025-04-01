package com.example.currencyexchanger2.currencyexchangescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyexchanger2.currencyexchangescreen.helpers.BalanceFormatter
import com.example.currencyexchanger2.currencyexchangescreen.helpers.ExchangeCalculator
import com.example.currencyexchanger2.currencyexchangescreen.data.ExchangeResult
import com.example.currencyexchanger2.currencyexchangescreen.managers.ProceedExchangeUseCase
import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.usecase.GetBalancesUseCase
import com.example.currencyexchanger2.currencyexchangescreen.data.CurrencyAmount
import com.example.currencyexchanger2.currencyexchangescreen.managers.transactions.IncrementTransactionCountUseCase
import com.example.currencyexchanger2.formatTo2Decimals
import com.example.currencyexchanger2.network.usecase.GetAvailableCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val getBalancesUseCase: GetBalancesUseCase,
    private val balanceFormatter: BalanceFormatter,
    private val proceedExchangeUseCase: ProceedExchangeUseCase,
    private val incrementTransactionCountUseCase: IncrementTransactionCountUseCase,
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

    fun confirmExchange() {
        viewModelScope.launch {
            with(_uiState.value) {
                proceedExchangeUseCase(
                    from = CurrencyAmount(amountToSell, currencyToSell),
                    to = CurrencyAmount((convertedAmount?.toDoubleOrNull() ?: 0.0), currencyToReceive),
                ).collect { result ->
                    when (result) {
                        is ExchangeResult.Success -> {
                            _uiState.update { it.copy(exchangeResult = result) }
                            incrementTransactionCountUseCase()
                        }

                        is ExchangeResult.Error -> {
                            _uiState.update { it.copy(exchangeResult = result) }
                        }
                    }
                }
            }
        }
    }

    fun dismissDialog() {
        _uiState.update { it.copy(exchangeResult = null) }
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
                _uiState.update { it.copy(balances = balanceFormatter.mapBalancesToDisplayList(balances)) }
            }
        }
    }

    private fun loadAvailableCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val currencies = getAvailableCurrenciesUseCase()
            _uiState.update { it.copy(availableCurrencies = currencies) }
        }
    }
}
