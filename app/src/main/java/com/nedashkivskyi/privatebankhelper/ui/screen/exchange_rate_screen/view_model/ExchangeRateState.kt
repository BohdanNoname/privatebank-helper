package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate_screen.view_model

data class ExchangeRateState(
    val isLoading: Boolean = false,
    val data: ExchangeRateData? = null,
    val errorMessage: String = ""
)
