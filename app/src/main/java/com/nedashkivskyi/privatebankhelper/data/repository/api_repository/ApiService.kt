package com.nedashkivskyi.privatebankhelper.data.repository.api_repository

import com.nedashkivskyi.privatebankhelper.common.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.ApiPrivatBankBaseUrl + "{format}" + "{date}")
    suspend fun getExchangeRate(
        @Path("format") format: String,
        @Path("date") date: String
    ): ExchangeRateData
}