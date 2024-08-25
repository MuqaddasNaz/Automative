package com.example.automotiveapp.Activities.Main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alhamdanautomotive.Utills.Constants
import com.example.automotiveapp.Activities.StartUp.LoginActivity
import com.example.automotiveapp.Model.Users
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.SharedPref
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {


    private lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val languageSelection = findViewById<TextView>(R.id.languageSelection)
        val privacyPolicy = findViewById<TextView>(R.id.privacyPolicy)
        val termsOfService = findViewById<TextView>(R.id.termsOfService)
        val dataSecurity = findViewById<TextView>(R.id.dataSecurity)

        val appVersion = findViewById<TextView>(R.id.appVersion)
        val logout = findViewById<TextView>(R.id.logout)



        languageSelection.setOnClickListener {
            val intent = Intent(this, LanguageSelectionActivity::class.java)
            startActivity(intent)        }



        privacyPolicy.setOnClickListener {
            val intent = Intent(this, PrivacyPolicyActivity::class.java)
            startActivity(intent)        }

        termsOfService.setOnClickListener {
            val intent = Intent(this, TermOfServicesActivity::class.java)
            startActivity(intent)
        }

        val dataSecurityTextView = findViewById<TextView>(R.id.dataSecurity)

        dataSecurityTextView.setOnClickListener {

            Toast.makeText(this@SettingsActivity, "Data Security Clicked", Toast.LENGTH_SHORT).show()
        }


        val appVersionTextView = findViewById<TextView>(R.id.appVersion)

        appVersionTextView.setOnClickListener {

            val versionName = packageManager.getPackageInfo(packageName, 0).versionName
            Toast.makeText(this@SettingsActivity, "App Version: $versionName", Toast.LENGTH_SHORT).show()
        }



        logout.setOnClickListener {
            openLogoutDialog()
        }

        appVersion.setOnClickListener {
            // Implement logic to display app version
            val appVersion = packageManager.getPackageInfo(packageName, 0).versionName
            Toast.makeText(this, "App Version: $appVersion", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLogoutDialog() {

        MaterialAlertDialogBuilder(this@SettingsActivity)
            .setTitle("Logout?")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                signOut()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .show()
    }

    private fun signOut() {

        FirebaseAuth.getInstance().signOut()
        sp.saveUsers(Users())
        sp.putBoolean(Constants.isLoggedIn,false)
        val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }


}
