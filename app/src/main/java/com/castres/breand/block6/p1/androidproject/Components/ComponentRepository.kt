package com.castres.breand.block6.p1.androidproject.Components

import com.castres.breand.block6.p1.androidproject.data.model.modeling.API

class ComponentRepository (private val api: API) {

    suspend fun getComponent() = api.getComponent()

}