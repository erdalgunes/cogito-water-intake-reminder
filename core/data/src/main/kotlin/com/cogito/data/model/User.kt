package com.cogito.data.model

import com.cogito.database.model.UserEntity
import com.cogito.model.data.UserDataModel

fun UserDataModel.asEntity() = UserEntity(
    name = name,
    email = email,
    goal = goal,
)