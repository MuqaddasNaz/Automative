package com.example.automotiveapp.Activities.Main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Functions
import com.example.automotiveapp.databinding.ActivityCarRepairingBinding
import com.example.automotiveapp.databinding.ActivityCarWashBinding

class CarWashActivity : AppCompatActivity() {

    private lateinit var imgCarRepair: ImageView
    private lateinit var imgCarWash: ImageView
    private lateinit var imgOilChange: ImageView


    private lateinit var binding: ActivityCarWashBinding // Declare binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarWashBinding.inflate(layoutInflater) // Initialise binding object
        setContentView(binding.root)


        imgCarWash = findViewById(R.id.img_car_wash)
        imgOilChange = findViewById(R.id.img_oil_change)
        imgCarRepair = findViewById(R.id.img_car_repair)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val btnWatsup: ImageView = findViewById(R.id.btn_wattsup)

        btnWatsup.setOnClickListener {

            val number = "+971507961525" // WhatsApp number yahan daalein
            val url = "https://api.whatsapp.com/send?phone=$number"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "WhatsApp is  installed on your device.", Toast.LENGTH_SHORT).show()
            }
        }

        imgCarWash.setOnClickListener {
            startActivity(Intent(this, CarWashActivity::class.java))
        }
        imgOilChange.setOnClickListener {
            startActivity(Intent(this, OilChangeActivity::class.java))
        }
        imgCarRepair.setOnClickListener {
            startActivity(Intent(this, CarRepairingActivity::class.java))
        }

    }
}