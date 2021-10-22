package com.nedashkivskyi.privatebankhelper.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Button
import androidx.compose.runtime.*
import com.nedashkivskyi.privatebankhelper.ui.viewModel.ExchangeRateViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.nedashkivskyi.privatebankhelper.ui.viewModel.ExchangeRateViewModel.ExchangeEvent
import com.nedashkivskyi.privatebankhelper.utils.Constants

private val TAG = "CurrencyRateScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExchangeRateScreen(viewModel: ExchangeRateViewModel) {
    val composableScope = rememberCoroutineScope()

    Button(onClick = {
        viewModel.getExchangeRate(
            format = Constants.ApiPrivatBankSearchFormat.ExchangeRatesJson,
            date = "19.10.2021")}) {
    }

    composableScope.launch {
        viewModel.networkState.collect { state ->
            when(state){
                is ExchangeEvent.Success ->
                {
                    Log.d(TAG, "SUCCESS")
                    for (rate in state.exchangeRateData.exchangeRate){
                        Log.d(TAG, rate.toString())
                    }
                }

                is ExchangeEvent.Error ->
                {
                    Log.d(TAG, "ERROR")
                    Log.d(TAG, state.errorMessage)
                }

                is ExchangeEvent.Loading -> {
                    Log.d(TAG, "LOADING")
                }

                is ExchangeEvent.Empty -> {
                    Log.d(TAG, "EMPTY")
                }
            }
        }
    }
}