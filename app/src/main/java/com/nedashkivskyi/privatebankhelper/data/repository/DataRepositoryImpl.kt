package com.nedashkivskyi.privatebankhelper.data.repository

import com.nedashkivskyi.privatebankhelper.data.db.DaoExchangeRate
import com.nedashkivskyi.privatebankhelper.data.db.DataBaseExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.network.ApiRepository
import com.nedashkivskyi.privatebankhelper.data.network.ApiService
import com.nedashkivskyi.privatebankhelper.utils.Constants
import com.nedashkivskyi.privatebankhelper.utils.Resource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataBaseExchangeRate: DataBaseExchangeRate
    ): ApiRepository {

    private val format: String = Constants.ApiPrivatBankSearchFormat.ExchangeRatesJson
    private val daoDataBase = dataBaseExchangeRate.daoExchangeRate()

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