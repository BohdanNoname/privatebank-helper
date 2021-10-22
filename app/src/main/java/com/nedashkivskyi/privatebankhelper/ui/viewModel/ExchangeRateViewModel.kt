package com.nedashkivskyi.privatebankhelper.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.privatebankhelper.models.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.network.ApiRepositoryImpl
import com.nedashkivskyi.privatebankhelper.utils.DispatcherProvider
import com.nedashkivskyi.privatebankhelper.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val apiRepository: ApiRepositoryImpl,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    private var _networkState = MutableStateFlow<ExchangeEvent>(ExchangeEvent.Empty)
    val networkState: StateFlow<ExchangeEvent> = _networkState

    fun getExchangeRate(format: String, date: String) {
        viewModelScope.launch(dispatchers.io()) {
            _networkState.value = ExchangeEvent.Loading
            when(val resource = apiRepository.getExchangeRate(format = format, date = date)){
                is Resource.Success -> _networkState.value = ExchangeEvent.Success(resource.data!!)
                is Resource.Error -> _networkState.value = ExchangeEvent.Error(resource.message)
            }
        }
    }

    sealed class ExchangeEvent{
        class Success(val exchangeRateData: ExchangeRateData): ExchangeEvent()
        class Error(val errorMessage: String): ExchangeEvent()
        object Loading: ExchangeEvent()
        object Empty: ExchangeEvent()
    }
}
