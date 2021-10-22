package com.nedashkivskyi.privatebankhelper.network

import com.nedashkivskyi.privatebankhelper.models.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.utils.Resource
import retrofit2.Response


interface ApiRepository {
    suspend fun getExchangeRate(format: String, date: String): Resource<ExchangeRateData>
}