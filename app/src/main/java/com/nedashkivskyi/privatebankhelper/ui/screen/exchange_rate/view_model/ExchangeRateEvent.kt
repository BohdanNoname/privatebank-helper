package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model

import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate

sealed class ExchangeRateEvent {
    class Success(val exchangeRateData: List<EntityExchangeRate>) : ExchangeRateEvent()
    class Error(val errorMessage: String) : ExchangeRateEvent()
    class Loading(val isLoading: Boolean) : ExchangeRateEvent()
    object Empty : ExchangeRateEvent()
}
