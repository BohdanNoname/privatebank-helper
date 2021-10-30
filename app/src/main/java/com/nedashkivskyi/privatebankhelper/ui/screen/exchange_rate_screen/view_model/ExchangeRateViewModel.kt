package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate_screen.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.privatebankhelper.common.Constants
import com.nedashkivskyi.privatebankhelper.common.DispatcherProvider
import com.nedashkivskyi.privatebankhelper.common.Resource
import com.nedashkivskyi.privatebankhelper.data.use_case.get_exchange_rate.GetExchangeRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val dispatchers: DispatcherProvider,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _networkState = mutableStateOf(ExchangeRateState())
    val networkState: State<ExchangeRateState> = _networkState

    init {
        Log.d("TAG", Constants.CurrentDate)
        getExchangeRate(date = Constants.CurrentDate)
    }

    fun getExchangeRate(date: String) {
        val format = Constants.ApiPrivatBankSearchFormat.ExchangeRatesJson
        getExchangeRateUseCase(format = format, date = date).onEach { result ->
            when(result){
                is Resource.Loading ->
                    _networkState.value = ExchangeRateState(isLoading = true)
                is Resource.Success ->
                    _networkState.value = ExchangeRateState(data = result.data)
                is Resource.Error ->
                    _networkState.value = ExchangeRateState(errorMessage = result.message)
            }
        }.launchIn(viewModelScope)
    }
}

//  viewModelScope.launch(dispatchers.io()) {
//            val format = Constants.ApiPrivatBankSearchFormat.ExchangeRatesJson
//
//            when(val resource = apiRepository.getExchangeRate(format = format, date = date)){
//                is Resource.Loading ->
//                    _networkState.value =
//                        ExchangeRateState.Loading(isLoading = true)
//
//                is Resource.Success ->
//                    _networkState.value =
//                        ExchangeRateState.Success(exchangeRateData = resource.data!!)
//
//                is Resource.Error ->
//                    _networkState.value =
//                        ExchangeRateState.Error(errorMessage = resource.message)
//            }
//        }
