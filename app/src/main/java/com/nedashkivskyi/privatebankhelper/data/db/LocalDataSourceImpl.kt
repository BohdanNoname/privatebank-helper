package com.nedashkivskyi.privatebankhelper.data.db

import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityCurrencyData
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRate
import com.nedashkivskyi.privatebankhelper.data.model.db_model.EntityExchangeRateData

class LocalDataSourceImpl(
    private val daoDataBaseExchangeRate: DaoExchangeRate
    ): DataBaseRepository {

    override suspend fun getAllCurrencyData(): List<EntityCurrencyData> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(
        entityExchangeRateData: EntityExchangeRateData,
        entityExchangeRate: EntityExchangeRate
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(entityExchangeRateData: EntityExchangeRateData) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(entityExchangeRate: EntityExchangeRate) {
        TODO("Not yet implemented")
    }

    override suspend fun update(data: EntityCurrencyData) {
        TODO("Not yet implemented")
    }
}