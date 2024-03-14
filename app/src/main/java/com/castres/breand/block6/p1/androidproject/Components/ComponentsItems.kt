package com.castres.breand.block6.p1.androidproject.Components

val COMPONENTS_ID_EXTRA = "componentsExtra"

data class ComponentsItems(
    var id: Int?,
    var prod_name: String,
    var image: String,
    var price: Double, // or String
    var category: String,
    var description: String,
)


data class ComponentsDetailItems(
    var id: Int?,
    var prod_name: String,
    var image: String,
    var price: Double, // or String
    var category: String,
    var description: String,
    var componentsAddToCart: Int, // change this to String


)



