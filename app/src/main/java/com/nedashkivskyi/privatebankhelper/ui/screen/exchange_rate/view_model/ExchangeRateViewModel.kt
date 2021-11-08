package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.privatebankhelper.data.repository.ExchangeRateRepository
import com.nedashkivskyi.privatebankhelper.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val repository: ExchangeRateRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    var state: MutableState<ExchangeRateEvent> = mutableStateOf(ExchangeRateEvent.Empty)

    fun getExchangeRate(date: String) {
        viewModelScope.launch(dispatchers.io()) {
            state.value = ExchangeRateEvent.Loading(isLoading = true)
            when(val event = repository.fetchExchangeRate(date = date)){
                is ExchangeRateEvent.Success ->
                    state.value = event
                is ExchangeRateEvent.Error ->
                    state.value = event
            }
        }
    }
}

