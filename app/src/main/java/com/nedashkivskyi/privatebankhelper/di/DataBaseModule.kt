package com.nedashkivskyi.privatebankhelper.di

import android.content.Context
import com.nedashkivskyi.privatebankhelper.data.db.DataBaseExchangeRate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDataBaseExchangeRate(@ApplicationContext context: Context):DataBaseExchangeRate =
        DataBaseExchangeRate.getDataBase(context = context)
}