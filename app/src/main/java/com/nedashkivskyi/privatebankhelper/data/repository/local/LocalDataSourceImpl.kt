package com.nedashkivskyi.privatebankhelper.data.repository.local

import com.nedashkivskyi.privatebankhelper.data.db.DataBaseExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.RelationEntityExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataBase: DataBaseExchangeRate
    ): DataBaseRepository {

    private val daoDataBaseExchangeRate = dataBase.daoExchangeRate()

    override suspend fun getAllCurrencyData(): List<RelationEntityExchangeRateData> =
        daoDataBaseExchangeRate.getAllCurrencyData()

    open override suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRateList: List<EntityExchangeRate>
    ) {
        if(getExchangeRateDataByDate(entityExchangeRateData.date) == null){
            val dataId = insert(entityExchangeRateData)

            for(rate in entityExchangeRateList){
                rate.dataId = dataId
                insert(rate)
            }
        }
    }

    override suspend fun insert(entityExchangeRateData: EntityExchangeRateData): Long =
        daoDataBaseExchangeRate.insert(entityExchangeRateData = entityExchangeRateData)


    override suspend fun insert(entityExchangeRate: EntityExchangeRate) =
        daoDataBaseExchangeRate.insert(entityExchangeRate = entityExchangeRate)

    override suspend fun getExchangeRateDataByDate(date: String): EntityExchangeRateData =
        daoDataBaseExchangeRate.getExchangeRateDataByDate(date = date)

    override suspend fun getExchangeRateListById(dataId: Long): List<EntityExchangeRate> =
        daoDataBaseExchangeRate.getExchangeRateListById(dataId = dataId)
}