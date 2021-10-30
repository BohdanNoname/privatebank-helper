package com.nedashkivskyi.privatebankhelper.data.model.db_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate_data")
data class EntityExchangeRateData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var dataId: Int,
    @ColumnInfo(name = "date")
    var date: String
)

@Entity(tableName = "exchange_rate")
data class EntityExchangeRate(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "currency_id")
    var currencyId: Int,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "base_currency")
    var baseCurrency: String,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "purchase_rate")
    var purchaseRate: Double,
    @ColumnInfo(name = "purchase_rate_NB")
    var purchaseRateNB: Double,
    @ColumnInfo(name = "sale_rate")
    var saleRate: Double,
    @ColumnInfo(name = "sale_rate_NB")
    var saleRateNB: Double
)
