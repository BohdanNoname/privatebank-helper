package com.nedashkivskyi.privatebankhelper.di

import com.nedashkivskyi.privatebankhelper.data.network.ApiService
import com.nedashkivskyi.privatebankhelper.data.repository.RemoteDataSourceImpl
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
    fun providesDataRepository(apiService: ApiService): RemoteDataSourceImpl =
        RemoteDataSourceImpl(apiService = apiService)
}