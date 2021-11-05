package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateViewModel
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateViewModel.ExchangeEvent
import com.nedashkivskyi.privatebankhelper.utils.Constants

private val TAG = "CurrencyRateScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExchangeRateScreen(viewModel: ExchangeRateViewModel) {

    LaunchedEffect(true){
        viewModel.getExchangeRate(Constants.CurrentDate)
    }
    

    when(val state = viewModel.networkState.value){
        is ExchangeEvent.Success ->
            CreateColumn(viewModel = viewModel, text = state.exchangeRateData.exchangeRate.toString())
        is ExchangeEvent.Loading ->
            CreateColumn(viewModel = viewModel, text = state.isLoading.toString())
        is ExchangeEvent.Error ->
        {
            CreateColumn(viewModel = viewModel, text = state.errorMessage)
            var e = state.errorMessage
            Log.d(TAG, "ExchangeRateScreen: $e")
        }
        is ExchangeEvent.Empty ->
            CreateColumn(viewModel = viewModel, text = "Empty")
    }
}

@Composable
fun CreateColumn(viewModel: ExchangeRateViewModel, text: String) {
    Column{
        Button(onClick = {
            viewModel.getExchangeRate(date = "19.10.2021")}) {
        }
        Text(text)
    }
}