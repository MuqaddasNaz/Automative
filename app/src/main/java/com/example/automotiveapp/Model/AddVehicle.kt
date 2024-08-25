package com.example.automotiveapp.Model

data class AddVehicle(

    val imageResource: Int,
    val title: String,
    val price: String,
    val saleType: String,
    val model: String,
    val exteriorColour: String,
    val modeYear: String,
    val condition: String,
    val contactNumber: String,
    var isLiked: Boolean
)
