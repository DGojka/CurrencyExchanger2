package com.example.currencyexchanger2.currencyexchangescreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val exchangeCalculator: ExchangeCalculator,
 //   private val balancesRepository: BalancesRepository
) : ViewModel() {

/*    private val _uiState = MutableStateFlow(ExchangeUiState())
    val uiState: StateFlow<ExchangeUiState> = _uiState.asStateFlow()

    init {
        loadBalances()
    }

    private fun loadBalances() {
        viewModelScope.launch {
            balancesRepository.getBalances().collect { balances ->
                _uiState.update { it.copy(balances = balances) }
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
        val state = _uiState.value
        viewModelScope.launch {
            exchangeCalculator.calculateExchange(
                amount = state.amountToSell,
                from = state.currencyToSell,
                to = state.currencyToReceive
            ).collect { convertedAmount ->
                _uiState.update { it.copy(convertedAmount = convertedAmount) }
            }
        }
    }*/
}