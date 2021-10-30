package com.nedashkivskyi.privatebankhelper.data.network

import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.ApiPrivatBankBaseUrl + "{format}" + "{date}")
    suspend fun getExchangeRate(
        @Path("format") format: String,
        @Path("date") date: String
    ): Response<ExchangeRateData>
}