package com.nedashkivskyi.privatebankhelper.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

@Database(
    entities = [EntityExchangeRateData::class, EntityExchangeRate::class],
    version = 1,
    exportSchema = false
)
public abstract class DataBaseExchangeRate: RoomDatabase() {

    abstract fun daoExchangeRate(): DaoExchangeRate

    companion object{
        @Volatile
        private var INSTANCE: DataBaseExchangeRate? = null

        fun getDataBase(context: Context): DataBaseExchangeRate{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseExchangeRate::class.java,
                    "database_exchange_rate"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}