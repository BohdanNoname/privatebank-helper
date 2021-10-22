package com.nedashkivskyi.privatebankhelper.network

import com.nedashkivskyi.privatebankhelper.models.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.network.ApiService
import com.nedashkivskyi.privatebankhelper.utils.Resource
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ): ApiRepository {

    override suspend fun getExchangeRate(format: String, date: String): Resource<ExchangeRateData> {
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