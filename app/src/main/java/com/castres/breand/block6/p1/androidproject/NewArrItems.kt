package com.castres.breand.block6.p1.androidproject

var newArrList = mutableListOf<NewArrItems>()

class NewArrItems(
    var cover: Int,
    var itemName: String,
    var itemPrice: String,
    var description: String,
    val id: Int? = newArrList.size
)
