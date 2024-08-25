package com.example.automotiveapp.Model

import com.example.automotiveapp.Model.CarForBuyItem
import java.io.Serializable

data class Vehicle(
    val id: String = "",
    val modelYear: String = "",
    var propertyPurpose: String = "",
    var propertyType: String = "",
    var city: String = "",
    var areaSize: String = "",
    var bedrooms: String = "",
    var bathrooms: String = "",
    val model: String = "",
    val make: String = "",
    val region: String = "",
    val engineCapacity: String = "",
    val price: String = "",
    val exterior: String = "",
    val version: String = "",
    val totalPrice: String = "",
    var vehicleDescription: String = "",
    var emailAddress: String = "",
    var contactNumber: String = "",
    var contactNumber1: String = "",
    var imgUrl: String = "",
    val name: String = ""
) : Serializable {

}
