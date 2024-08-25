package com.example.automotiveapp.Activities.Main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Adapter.VehicleCategoryAdapter
import com.example.automotiveapp.Model.CarBrand
import com.example.automotiveapp.R

class VehicleCategoryActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleCategoryAdapter: VehicleCategoryAdapter
    private var carBrandsList = ArrayList<CarBrand>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_category)


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rv_CarBrands)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        carBrandsList = generateCarBrandsList()

        vehicleCategoryAdapter = VehicleCategoryAdapter(carBrandsList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vehicleCategoryAdapter
    }

    private fun generateCarBrandsList(): ArrayList<CarBrand> {
        // Create a sample list of car brands
        val brandsList = ArrayList<CarBrand>()
        brandsList.add(CarBrand("VOLKSWAGEN", R.drawable.brand_img))
        brandsList.add(CarBrand("MAZDA", R.drawable.brand_img10))
        brandsList.add(CarBrand("TOYOTA", R.drawable.brand_img11))
        brandsList.add(CarBrand("MERCEDES", R.drawable.brand_img1))
        brandsList.add(CarBrand("BMW", R.drawable.brand_img2))
        brandsList.add(CarBrand("HONDA", R.drawable.brand_img3))
        brandsList.add(CarBrand("FORD", R.drawable.brand_img4))
        brandsList.add(CarBrand("JEEP", R.drawable.brand_img5))
        brandsList.add(CarBrand("AUDI", R.drawable.brand_img6))
        brandsList.add(CarBrand("HYUNDAI", R.drawable.brand_img7))
        brandsList.add(CarBrand("SUBARU", R.drawable.brand_img8))
        brandsList.add(CarBrand("PORSCHE", R.drawable.brand_img9))
        brandsList.add(CarBrand("MAZDA", R.drawable.brand_img10))
        brandsList.add(CarBrand("VOLKSWAGEN", R.drawable.brand_img))
        // Add more brands as needed
        return brandsList
    }

    private fun CarBrand(brandName: String): CarBrand {
        return CarBrand(brandName, R.drawable.brand_img10)
    }
}