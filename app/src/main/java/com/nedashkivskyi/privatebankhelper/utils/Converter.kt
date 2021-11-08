package com.nedashkivskyi.privatebankhelper.utils

import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.model.network_model.ExchangeRateData

object Converter {
    fun convertToEntityExchangeRateData(exchangeRateData: ExchangeRateData): EntityExchangeRateData {
        return EntityExchangeRateData(dataId = 0, date = exchangeRateData.date)
    }

    fun convertToEntityExchangeRateList(exchangeRateList: List<ExchangeRateData.ExchangeRate>): List<EntityExchangeRate> {
        val list = mutableListOf<EntityExchangeRate>()

        for((index, rate) in exchangeRateList.withIndex()){
            list.add(index, EntityExchangeRate(
                currencyId = 0,
                dataId = 0,
                baseCurrency = rate.baseCurrency,
                currency = rate.currency,
                purchaseRate = rate.purchaseRate,
                purchaseRateNB = rate.purchaseRateNB,
                saleRate = rate.saleRate,
                saleRateNB = rate.saleRateNB)
            )
        }
        return list
    }
}
