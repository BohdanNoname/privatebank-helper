package com.nedashkivskyi.privatebankhelper.di

import com.nedashkivskyi.privatebankhelper.data.repository.api_repository.ApiRepositoryImpl
import com.nedashkivskyi.privatebankhelper.data.repository.api_repository.ApiService
import com.nedashkivskyi.privatebankhelper.common.Constants
import com.nedashkivskyi.privatebankhelper.common.DispatcherProvider
import com.nedashkivskyi.privatebankhelper.data.use_case.get_exchange_rate.GetExchangeRateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.ApiPrivatBankBaseUrl)
            .build()
            .create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesApiRepository(apiService: ApiService): ApiRepositoryImpl =
        ApiRepositoryImpl(apiService = apiService)


    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = object :
        DispatcherProvider {
        override fun main(): CoroutineDispatcher =
            Dispatchers.Main

        override fun io(): CoroutineDispatcher =
            Dispatchers.IO

        override fun default(): CoroutineDispatcher =
            Dispatchers.Default

        override fun unconfined(): CoroutineDispatcher =
            Dispatchers.Unconfined
    }
}