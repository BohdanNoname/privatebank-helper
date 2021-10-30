package com.nedashkivskyi.privatebankhelper.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateData(
    @SerialName("date")
    var date: String,
    @SerialName("bank")
    var bank: String,
    @SerialName("baseCurrency")
    var baseCurrency: Int,
    @SerialName("baseCurrencyLit")
    var baseCurrencyLit: String,
    @SerialName("exchangeRate")
    var exchangeRate: List<ExchangeRate>
) {
    @Serializable
    data class ExchangeRate(
        @SerialName("baseCurrency")
        var baseCurrency: String,
        @SerialName("currency")
        var currency: String,
        @SerialName("saleRateNB")
        var saleRateNB: Double,
        @SerialName("purchaseRateNB")
        var purchaseRateNB: Double,
        @SerialName("saleRate")
        var saleRate: Double,
        @SerialName("purchaseRate")
        var purchaseRate: Double
    )
}