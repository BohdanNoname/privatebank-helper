package com.nedashkivskyi.privatebankhelper.data.db

import androidx.room.*
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityCurrencyData

@Dao
interface DaoExchangeRate {
    @Transaction
    @Query("SELECT * from exchange_rate_data")
    suspend fun getAllCurrencyData(): List<EntityCurrencyData>
}