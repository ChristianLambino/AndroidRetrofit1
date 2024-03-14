package com.castres.breand.block6.p1.androidproject.dataclass

import com.castres.breand.block6.p1.androidproject.Components.ComponentsItems

data class ItemRequest(
    var id: Int?,
    var prod_name: String,
    var image: String, // Change the type to String
    var price: Double,
    var category: String,
    var description: String,
    var componentsAddToCart: Int
)

data class ItemResponse(
    val components: ComponentsItems,
    val token: Token// Change the type to String or whatever type your token is
)




