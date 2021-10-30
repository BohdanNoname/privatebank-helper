package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nedashkivskyi.privatebankhelper.data.model.ExchangeRateData.ExchangeRate
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate_screen.view_model.ExchangeRateViewModel

private val TAG = "CurrencyRateScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExchangeRateScreen(viewModel: ExchangeRateViewModel) {
    val composableScope = rememberCoroutineScope()
    val state = viewModel.networkState.value

    Button(
        onClick = {
            viewModel.getExchangeRate(
                date = "19.10.2021")
        }) {
        Text(text = "getExchangeRate")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        item(state.data){
            val data = state.data.toString()
            Log.d(TAG, "ExchangeRateScreen data: $data")
        }
        item(state.isLoading){
            val loading = state.data.toString()
            Log.d(TAG, "ExchangeRateScreen loading: $loading")

        }
        item(state.errorMessage){
            val errorMessage = state.data.toString()
            Log.d(TAG, "ExchangeRateScreen errorMessage: $errorMessage")
        }
    }
}


@Composable
fun ExchangeRateCard(
    rate: ExchangeRate,
    onClick: () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium
            ),
        elevation = 8.dp)
    {
        Row(
            modifier = Modifier.
                    fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.
                        weight(1f)
            ) {
                Text(text = "baseCurrency: ${rate.baseCurrency}")/*1*/
                Text(text = "saleRateNB: ${rate.saleRateNB}")/*2*/
                Text(text = "saleRate: ${rate.saleRate}")/*3*/
            }

            Column(
                modifier = Modifier.
                    weight(1f)
            ) {
                Text(text = "currency: ${rate.currency}")/*1*/
                Text(text = "purchaseRateNB: ${rate.purchaseRateNB}")/*2*/
                Text(text = "purchaseRate: ${rate.purchaseRate}")/*3*/
            }
        }
    }
}