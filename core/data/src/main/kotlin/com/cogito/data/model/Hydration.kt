package com.cogito.data.model

import com.cogito.database.model.HydrationEntity
import com.cogito.model.data.HydrationDataModel
import com.cogito.network.model.HydrationNetworkModel
import kotlinx.datetime.Clock

fun HydrationDataModel.asEntity() = HydrationEntity(
    amount = amountMilliliters,
    createdAt = createdAt,
)

fun HydrationDataModel.asNetworkModel(userId: String) = HydrationNetworkModel(
    userId = userId,
    amountMilliliters = amountMilliliters,
    createdAt = createdAt.toString()
)

fun HydrationEntity.asDataModel() = HydrationDataModel(
    amountMilliliters = amount,
    createdAt = createdAt,
)

fun HydrationEntity.asNetworkModel(userId: String) = HydrationNetworkModel(
    userId = userId,
    amountMilliliters = amount,
    createdAt = createdAt.toString()
)