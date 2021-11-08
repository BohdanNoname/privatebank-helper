package com.nedashkivskyi.privatebankhelper.data.repository.remote

import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.network.ApiService
import com.nedashkivskyi.privatebankhelper.utils.Constants
import com.nedashkivskyi.privatebankhelper.utils.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
    ): ApiRepository {

    private val format: String = Constants.ApiPrivatBankSearchFormat.ExchangeRatesJson

    override suspend fun getExchangeRate(date: String): Resource<ExchangeRateData> {
        return try {
            val response = apiService.getExchangeRate(format = format, date = date)
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        }
        catch (e: Exception){
            Resource.Error(e.message?:"An error occurred")
        }
    }
}