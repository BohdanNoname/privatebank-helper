package com.nedashkivskyi.privatebankhelper.data.db

import androidx.room.*
import com.nedashkivskyi.privatebankhelper.data.model.db_model.RelationEntityExchangeRateData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

@Dao
interface DaoExchangeRate {
    @Transaction
    @Query("SELECT * from exchange_rate_data")
    suspend fun getAllCurrencyData(): List<RelationEntityExchangeRateData>

    @Transaction
    @Insert
    suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRateList: List<EntityExchangeRate>
    )
    @Query("SELECT * from  exchange_rate_data WHERE :date LIKE date")
    suspend fun getExchangeRateDataByDate(date: String): EntityExchangeRateData

    @Query("SELECT * from exchange_rate WHERE :dataId LIKE data_id")
    suspend fun getExchangeRateListById(dataId: Long): List<EntityExchangeRate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entityExchangeRateData: EntityExchangeRateData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entityExchangeRate: EntityExchangeRate)
}