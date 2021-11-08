package com.nedashkivskyi.privatebankhelper.data.repository

import com.nedashkivskyi.privatebankhelper.data.repository.local.LocalDataSourceImpl
import com.nedashkivskyi.privatebankhelper.data.repository.remote.RemoteDataSourceImpl
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateEvent
import com.nedashkivskyi.privatebankhelper.utils.Converter
import com.nedashkivskyi.privatebankhelper.utils.Resource
import javax.inject.Inject

class ExchangeRateRepository @Inject constructor(
    private val remote: RemoteDataSourceImpl,
    private val local : LocalDataSourceImpl
) {

    suspend fun fetchExchangeRate(date: String): ExchangeRateEvent {
        val exchangeRateData = local.getExchangeRateDataByDate(date)

        if(exchangeRateData == null){
            when(val resource = remote.getExchangeRate(date = date)){
                is Resource.Success ->
                {
                    val entityExchangeRateData =
                        Converter.convertToEntityExchangeRateData(resource.data!!)

                    val entityExchangeRateList =
                        Converter.convertToEntityExchangeRateList(resource.data.exchangeRate)

                    local.insert(
                        entityExchangeRateData = entityExchangeRateData,
                        entityExchangeRateList = entityExchangeRateList
                    )
                    return ExchangeRateEvent.Success(entityExchangeRateList)
                }
                is Resource.Error ->
                    return ExchangeRateEvent.Error(resource.message)
            }

        } else {
            val exchangeRateList =
                local.getExchangeRateListById(exchangeRateData.dataId.toLong())
            return ExchangeRateEvent.Success(exchangeRateList)
        }
    }
}