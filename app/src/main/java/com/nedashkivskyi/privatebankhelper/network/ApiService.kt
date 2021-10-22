package com.nedashkivskyi.privatebankhelper.network

import com.nedashkivskyi.privatebankhelper.models.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.ApiPrivatBankBaseUrl + "{format}" + "{date}")
    suspend fun getExchangeRate(
        @Path("format") format: String,
        @Path("date") date: String
    ): Response<ExchangeRateData>
}