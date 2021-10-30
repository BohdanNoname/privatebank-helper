package com.nedashkivskyi.privatebankhelper.data.repository.api_repository


interface ApiRepository {
    suspend fun getExchangeRate(format: String, date: String): ExchangeRateData
}