package com.example.alhamdanautomotive.Utills

import com.google.firebase.database.FirebaseDatabase

object Constants {


    const val GOOGLE = "google"
    // Other constants...

    val users: String = "users"
    val vehicalDetail: String = "vehicalDetail"
    val viewListing: String = "viewListing"
    var KEY_PREFERENCE_NAME = "KEY_PREFERENCE_NAME"
    var isLoggedIn = "isLoggedIn"


    val usersRef = FirebaseDatabase.getInstance().getReference("users")

}