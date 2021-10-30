package com.nedashkivskyi.privatebankhelper.data.repository.network

import com.nedashkivskyi.privatebankhelper.data.model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.utils.Resource


interface ApiRepository {
    suspend fun getExchangeRate(date: String): Resource<ExchangeRateData>
}