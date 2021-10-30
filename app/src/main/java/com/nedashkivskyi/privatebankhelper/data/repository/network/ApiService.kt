package com.nedashkivskyi.privatebankhelper.data.repository.network

import com.nedashkivskyi.privatebankhelper.data.model.ExchangeRateData
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