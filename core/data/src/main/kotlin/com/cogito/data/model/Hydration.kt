package com.cogito.data.model

import com.cogito.database.model.HydrationEntity
import com.cogito.model.data.HydrationDataModel

fun HydrationDataModel.asEntity(userId: String) = HydrationEntity(
    userId = userId,
    amount = amount,
    timestamp = timestamp,
)