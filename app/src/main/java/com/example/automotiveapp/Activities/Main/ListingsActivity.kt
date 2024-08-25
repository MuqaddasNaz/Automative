package com.example.automotiveapp.Activities.Main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Adapter.CarForRentAdapter
import com.example.automotiveapp.Model.CarForRentItem
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Functions
import com.example.automotiveapp.databinding.ActivityContactUsBinding
import com.example.automotiveapp.databinding.ActivityListingsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListingsActivity : AppCompatActivity() {


    private lateinit var expandableMenu1: ScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityListingsBinding
    private lateinit var carForRentAdapter: CarForRentAdapter
    private var carForRentList = ArrayList<CarForRentItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingsBinding.inflate(layoutInflater) // Initialise binding object
        setContentView(binding.root)
        recyclerView = findViewById(R.id.rv_carsForRent)
        setupRecyclerView()
        initUI()


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    private fun initUI() {
        expandableMenu1 = findViewById(R.id.expandable_menu1)


        val btnFilter: Button = findViewById(R.id.btn_filter)
        val expandableMenu1: ScrollView = findViewById(R.id.expandable_menu1)

        btnFilter.setOnClickListener {
            if (expandableMenu1.visibility == View.VISIBLE) {
                expandableMenu1.visibility = View.GONE
            } else {
                expandableMenu1.visibility = View.VISIBLE
            }        }
    }

    private fun setupRecyclerView() {
        // Sample car for rent list
        val carForRentList = listOf(
            CarForRentItem("Toyota Camry", "Silver", "2022", "450", R.drawable.img_car),
            CarForRentItem("Honda Civic", "Red", "2021", "400", R.drawable.imag_car1),
            CarForRentItem("Tesla Model 3", "White", "2023", "600", R.drawable.img_car2),
            CarForRentItem("Tesla Model 3", "Red", "2024", "700", R.drawable.img_car3),
            CarForRentItem("Tesla Model 3", "Blue", "2021", "700", R.drawable.img_car4),
            CarForRentItem("Tesla Model 3", "Black", "2021", "500", R.drawable.img_car5),
            CarForRentItem("Tesla Model 3", "White", "2023", "800", R.drawable.img_car6),
            CarForRentItem("Toyota Camry", "Yellow", "2024", "600", R.drawable.img_car),
            CarForRentItem("Honda Civic", "Blue", "2024", "700", R.drawable.imag_car1),
            CarForRentItem("Tesla Model 3", "White", "2022", "700", R.drawable.img_car2),
            CarForRentItem("Tesla Model 3", "Red", "2024", "500", R.drawable.img_car3),
            CarForRentItem("Honda Civic", "Red", "2023", "400", R.drawable.img_car4),
            CarForRentItem("Toyota Camry", "White", "2022", "800", R.drawable.img_car5),
            CarForRentItem("Tesla Model 3", "Blue", "2022", "700", R.drawable.img_car6),
            CarForRentItem("Honda Civic", "White", "2024", "600", R.drawable.img_car)
        )
        carForRentAdapter = CarForRentAdapter(carForRentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = carForRentAdapter
    }
}
