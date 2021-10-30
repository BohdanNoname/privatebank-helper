package com.nedashkivskyi.privatebankhelper.data.use_case.get_exchange_rate

import com.nedashkivskyi.privatebankhelper.common.Resource
import com.nedashkivskyi.privatebankhelper.data.repository.api_repository.ApiRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val apiRepositoryImpl: ApiRepositoryImpl
){
    operator fun invoke(format: String, date: String): Flow<Resource<ExchangeRateData>> = flow {
        try {
            emit(Resource.Loading<ExchangeRateData>(isLoading = true))
            val exchangeRateData =
                apiRepositoryImpl.getExchangeRate(format = format, date = date)
            emit(Resource.Success<ExchangeRateData>(data = exchangeRateData))
        } catch (e: HttpException){
            emit(Resource.Error<ExchangeRateData>(message = e.message()))
        } catch (e: IOException){
            emit(Resource.Error<ExchangeRateData>(message = e.message.toString()))
        }
    }
}