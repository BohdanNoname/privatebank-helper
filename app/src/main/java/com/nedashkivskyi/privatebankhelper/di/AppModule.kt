package com.nedashkivskyi.privatebankhelper.di

import com.nedashkivskyi.privatebankhelper.data.network.ApiService
import com.nedashkivskyi.privatebankhelper.data.repository.ExchangeRateRepository
import com.nedashkivskyi.privatebankhelper.data.repository.local.LocalDataSourceImpl
import com.nedashkivskyi.privatebankhelper.data.repository.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun providesExchangeRateRepository(
        localDataSourceImpl: LocalDataSourceImpl,
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): ExchangeRateRepository =
        ExchangeRateRepository(local = localDataSourceImpl, remote = remoteDataSourceImpl)
}