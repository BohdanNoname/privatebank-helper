package com.nedashkivskyi.privatebankhelper.data.model


import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateData(
    @SerialName("bank")
    var bank: String,
    @SerialName("baseCurrency")
    var baseCurrency: Int,
    @SerialName("baseCurrencyLit")
    var baseCurrencyLit: String,
    @SerialName("date")
    var date: String,
    @SerialName("exchangeRate")
    var exchangeRate: List<ExchangeRate>
) {
    @Serializable
    data class ExchangeRate(
        @SerialName("baseCurrency")
        var baseCurrency: String,
        @SerialName("currency")
        var currency: String,
        @SerialName("purchaseRate")
        var purchaseRate: Double,
        @SerialName("purchaseRateNB")
        var purchaseRateNB: Double,
        @SerialName("saleRate")
        var saleRate: Double,
        @SerialName("saleRateNB")
        var saleRateNB: Double
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExchangeRateData

        if (bank != other.bank) return false
        if (baseCurrency != other.baseCurrency) return false
        if (baseCurrencyLit != other.baseCurrencyLit) return false
        if (date != other.date) return false
        if (exchangeRate != other.exchangeRate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bank.hashCode()
        result = 31 * result + baseCurrency
        result = 31 * result + baseCurrencyLit.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + exchangeRate.hashCode()
        return result
    }

    override fun toString(): String {
        return "ExchangeRateData(bank='$bank'," +
                " baseCurrency=$baseCurrency," +
                " baseCurrencyLit='$baseCurrencyLit'," +
                " date='$date'," +
                " exchangeRate=$exchangeRate)"
    }


}