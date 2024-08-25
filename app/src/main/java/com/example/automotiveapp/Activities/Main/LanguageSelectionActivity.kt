package com.example.automotiveapp.Activities.Main

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.automotiveapp.Activities.StartUp.MainActivity
import com.example.automotiveapp.R

class LanguageSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)

        val btnEnglish = findViewById<Button>(R.id.buttonEnglish)
        val btnUrdu = findViewById<Button>(R.id.buttonUrdu)
        val btnArabic = findViewById<Button>(R.id.buttonArabic)

        // Set click listeners for language buttons
        btnEnglish.setOnClickListener {
            setLanguage("en") // Set English as the selected language
        }

        btnUrdu.setOnClickListener {
            setLanguage("ur") // Set Urdu as the selected language
        }

        btnArabic.setOnClickListener {
            setLanguage("ar") // Set Arabic as the selected language
        }

        // Add more click listeners for other language buttons as needed
    }

    private fun setLanguage(languageCode: String) {
        // Save the selected language preference
        saveLanguagePreference(languageCode)

        // Update the app's configuration with the new language
        updateAppLanguage(languageCode)

        // Restart the app to apply the language change
        restartApp()
    }

    private fun saveLanguagePreference(languageCode: String) {
        // Here you can save the selected language preference using SharedPreferences or any other method
        // For demonstration purposes, we'll simply use SharedPreferences to save the language code
        val sharedPreferences: SharedPreferences = getSharedPreferences("LanguagePrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("language", languageCode)
        editor.apply()
    }

    private fun updateAppLanguage(languageCode: String) {
        // Update the app's configuration with the new language
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(java.util.Locale(languageCode))
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun restartApp() {
        // Restart the app to apply the language change
        val intent = Intent(this, MainActivity::class.java) // Replace MainActivity with your app's main activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finishAffinity()
    }
}
