package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedashkivskyi.privatebankhelper.data.db.DataBaseExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.repository.RemoteDataSourceImpl
import com.nedashkivskyi.privatebankhelper.utils.Converter
import com.nedashkivskyi.privatebankhelper.utils.DispatcherProvider
import com.nedashkivskyi.privatebankhelper.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val dataBaseExchangeRate: DataBaseExchangeRate,
    private val dispatchers: DispatcherProvider
): ViewModel() {
    private val daoDataBase = dataBaseExchangeRate.daoExchangeRate()

    var networkState: MutableState<ExchangeEvent> = mutableStateOf(ExchangeEvent.Empty)

    fun getExchangeRate(date: String) {
        viewModelScope.launch(dispatchers.io()) {
            networkState.value = ExchangeEvent.Loading(isLoading = true)
            when (val resource = remoteDataSource.getExchangeRate(date = date)) {
                is Resource.Success -> {
                    val result = resource.data!!
                    networkState.value = ExchangeEvent.Success(result)

                    val entityExchangeRateData =
                        Converter.convertToEntityExchangeRateData(result)
                    val entityExchangeRate =
                        Converter.convertToEntityExchangeRate(result.exchangeRate)

                    daoDataBase.insert(
                        entityExchangeRateData = entityExchangeRateData,
                        entityExchangeRate = entityExchangeRate
                    )
                }
                is Resource.Error ->
                    networkState.value = ExchangeEvent.Error(resource.message)
            }
        }
    }

    sealed class ExchangeEvent {
        class Success(val exchangeRateData: ExchangeRateData) : ExchangeEvent()
        class Error(val errorMessage: String) : ExchangeEvent()
        class Loading(val isLoading: Boolean) : ExchangeEvent()
        object Empty : ExchangeEvent()
    }
}

