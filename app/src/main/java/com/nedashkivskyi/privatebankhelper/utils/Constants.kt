package com.nedashkivskyi.privatebankhelper.utils

sealed class Constants {
    companion object{
        const val CurrencyRate = "currencyRate"
        const val BanksLocation = "banksLocation"
        const val CashpointLocation = "ATM_location"

        const val ApiPrivatBankBaseUrl = "https://api.privatbank.ua/p24api/"
        const val LoadingError: String = "Во время загрузки произошла ошибка!"
    }

    sealed class ApiPrivatBankSearchFormat {
        companion object{
            const val ExchangeRatesJson = "exchange_rates?json&date="
        }
    }
}