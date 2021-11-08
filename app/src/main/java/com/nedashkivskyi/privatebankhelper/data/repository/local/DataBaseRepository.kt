package com.nedashkivskyi.privatebankhelper.data.repository.local


import com.nedashkivskyi.privatebankhelper.data.model.db_model.RelationEntityExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

interface DataBaseRepository {
    suspend fun getAllCurrencyData(): List<RelationEntityExchangeRateData>
    open suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRateList: List<EntityExchangeRate>
    )
    suspend fun getExchangeRateDataByDate(date: String): EntityExchangeRateData
    suspend fun getExchangeRateListById(dataId: Long): List<EntityExchangeRate>
    suspend fun insert(entityExchangeRateData: EntityExchangeRateData): Long
    suspend fun insert(entityExchangeRate: EntityExchangeRate)

}