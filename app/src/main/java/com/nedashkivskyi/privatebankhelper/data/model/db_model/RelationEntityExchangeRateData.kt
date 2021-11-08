package com.nedashkivskyi.privatebankhelper.data.model.db_model

import androidx.room.Embedded
import androidx.room.Relation


data class RelationEntityExchangeRateData (
    @Embedded
    val data: EntityExchangeRateData,
    @Relation(
        parentColumn = "id",
        entityColumn = "currency_id")
    val exchangeRate: EntityExchangeRate
)
