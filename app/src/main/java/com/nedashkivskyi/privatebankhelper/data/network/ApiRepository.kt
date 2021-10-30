package com.nedashkivskyi.privatebankhelper.data.network

import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.utils.Resource


interface ApiRepository {
    suspend fun getExchangeRate(date: String): Resource<ExchangeRateData>
}