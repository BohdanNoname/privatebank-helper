package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateEvent
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateViewModel
import com.nedashkivskyi.privatebankhelper.utils.Constants

private val TAG = "CurrencyRateScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExchangeRateScreen(viewModel: ExchangeRateViewModel) {

    LaunchedEffect(true){
        viewModel.getExchangeRate(Constants.CurrentDate)
    }

    when(val state = viewModel.state.value){
        is ExchangeRateEvent.Success ->
            CreateColumn(viewModel = viewModel, list = state.exchangeRateData)
        is ExchangeRateEvent.Loading ->
            LoadingScreenPart(text = state.isLoading.toString())
        is ExchangeRateEvent.Error ->
            ErrorScreenPart(text = state.errorMessage)
    }
}

@Composable
fun CreateColumn(viewModel: ExchangeRateViewModel, list: List<EntityExchangeRate>) {
    Column{
        Row()
        {
            var date by remember{ mutableStateOf(Constants.CurrentDate)}
            Button(onClick = {
                viewModel.getExchangeRate(date = date)}) {
            }
            TextField(value = date, onValueChange = { date = it })
        }
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
        ){
            items(list){
                Text(text = it.currency + it.saleRate)
            }
        }
    }
}

@Composable
fun LoadingScreenPart(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = text)
    }
}

@Composable
fun ErrorScreenPart(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = text)
    }
}