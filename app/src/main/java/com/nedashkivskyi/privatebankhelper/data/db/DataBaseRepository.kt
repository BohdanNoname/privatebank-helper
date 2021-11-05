package com.nedashkivskyi.privatebankhelper.data.db

import androidx.room.*
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityCurrencyData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

interface DataBaseRepository {
    suspend fun getAllCurrencyData(): List<EntityCurrencyData>

    suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRate: EntityExchangeRate)

    suspend fun insert(entityExchangeRateData: EntityExchangeRateData)
    suspend fun insert(entityExchangeRate: EntityExchangeRate)
    suspend fun update(data: EntityCurrencyData)
}