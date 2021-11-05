package com.nedashkivskyi.privatebankhelper.data.model.db_model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "exchange_rate_data")
data class EntityExchangeRateData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var dataId: Int,
    @ColumnInfo(name = "date")
    var date: String
)

@Entity(
    tableName = "exchange_rate",
    foreignKeys = [ForeignKey(
        entity = EntityExchangeRateData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("data_id"),
        onDelete = CASCADE)],
    indices = [Index("data_id")]
)
data class EntityExchangeRate(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "currency_id")
    var currencyId: Int,
    @ColumnInfo(name = "data_id")
    var dataId: Long,
    @ColumnInfo(name = "base_currency")
    var baseCurrency: String,
    @ColumnInfo(name = "currency")
    var currency: String?,
    @ColumnInfo(name = "purchase_rate")
    var purchaseRate: Double,
    @ColumnInfo(name = "purchase_rate_NB")
    var purchaseRateNB: Double,
    @ColumnInfo(name = "sale_rate")
    var saleRate: Double,
    @ColumnInfo(name = "sale_rate_NB")
    var saleRateNB: Double
)
