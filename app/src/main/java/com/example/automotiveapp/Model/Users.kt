package com.example.automotiveapp.Model

import android.provider.ContactsContract.CommonDataKinds.Phone
import java.io.Serializable

data class Users(
    var uid: String = "",
    var firstName: String  = "",
    var lastName: String  = "",
    var phone: String  = "",
    var email: String  = "",
    var dpUrl: String = ""

)


