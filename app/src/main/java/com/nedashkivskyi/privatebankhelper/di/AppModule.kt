package com.nedashkivskyi.privatebankhelper.di

import com.nedashkivskyi.privatebankhelper.data.db.DaoExchangeRate
import com.nedashkivskyi.privatebankhelper.data.db.DataBaseExchangeRate
import com.nedashkivskyi.privatebankhelper.data.network.ApiService
import com.nedashkivskyi.privatebankhelper.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDataRepository(
        apiService: ApiService,
        dataBaseExchangeRate: DataBaseExchangeRate
    ): DataRepositoryImpl =
        DataRepositoryImpl(apiService = apiService, dataBaseExchangeRate = dataBaseExchangeRate)

}