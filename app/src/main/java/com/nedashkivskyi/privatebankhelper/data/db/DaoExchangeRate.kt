package com.nedashkivskyi.privatebankhelper.data.db

import androidx.room.*
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityCurrencyData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

@Dao
abstract class DaoExchangeRate {
    @Transaction
    @Query("SELECT * from exchange_rate_data")
    abstract suspend fun getAllCurrencyData(): List<EntityCurrencyData>

    @Transaction
    open suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRate: List<EntityExchangeRate>
    ) {
        if(getExchangeRateByDate(entityExchangeRateData.date) == null){
            val dataId = insert(entityExchangeRateData)
            for(rate in entityExchangeRate){
                rate.dataId = dataId
                insert(rate)
            }
        }
    }

    @Query("SELECT * from  exchange_rate_data WHERE :date LIKE date")
    abstract suspend fun getExchangeRateByDate(date: String): EntityExchangeRateData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entityExchangeRateData: EntityExchangeRateData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entityExchangeRate: EntityExchangeRate)
}