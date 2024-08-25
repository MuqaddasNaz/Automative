package com.example.automotiveapp.Activities.Main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Functions
import com.example.automotiveapp.databinding.ActivityChauffeurServiceBinding
import com.example.automotiveapp.databinding.ActivityContactUsBinding

class ContactUsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityContactUsBinding // Declare binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater) // Initialise binding object
        setContentView(binding.root)


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}