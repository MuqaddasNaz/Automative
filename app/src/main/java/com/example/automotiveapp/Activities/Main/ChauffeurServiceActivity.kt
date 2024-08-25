package com.example.automotiveapp.Activities.Main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Functions
import com.example.automotiveapp.databinding.ActivityCarRepairingBinding
import com.example.automotiveapp.databinding.ActivityChauffeurServiceBinding

class ChauffeurServiceActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChauffeurServiceBinding // Declare binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChauffeurServiceBinding.inflate(layoutInflater) // Initialise binding object
        setContentView(binding.root)


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val btnWatsup1: ImageView = findViewById(R.id.btn_wattsup1)

        btnWatsup1.setOnClickListener {

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

        val btnWatsup2: ImageView = findViewById(R.id.btn_wattsup2)

        btnWatsup2.setOnClickListener {

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
        val btnWatsup3: ImageView = findViewById(R.id.btn_wattsup3)

        btnWatsup3.setOnClickListener {

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
        val btnWatsup4: ImageView = findViewById(R.id.btn_wattsup4)

        btnWatsup4.setOnClickListener {

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
    }
}