package com.nedashkivskyi.privatebankhelper.data.db

import androidx.room.*
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityCurrencyData

interface DataBaseRepository {
    suspend fun getAllCurrencyData(): List<EntityCurrencyData>
    suspend fun insert(data: EntityCurrencyData)
    suspend fun update(data: EntityCurrencyData)
}