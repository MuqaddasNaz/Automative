package com.example.automotiveapp.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.core.content.ContextCompat
import com.example.automotiveapp.databinding.FragmentHomeBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Activities.Main.*
import com.example.automotiveapp.Activities.StartUp.LoginActivity
import com.example.automotiveapp.Adapter.VehicleCategoryAdapter
import com.example.automotiveapp.Model.CarBrand
import com.example.automotiveapp.R

class HomeFragment : Fragment() {

    private lateinit var btnAddVehicle: Button
    private lateinit var imgCarRepair: ImageView
    private lateinit var imgCarWash: ImageView
    private lateinit var imgOilChange: ImageView


    private lateinit var binding: FragmentHomeBinding

    private val SCROLL_SPEED = 530
    private lateinit var leftIcon: ImageButton
    private lateinit var rightIcon: ImageButton

    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleCategoryAdapter: VehicleCategoryAdapter
    private var carBrandsList = ArrayList<CarBrand>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Hide the status bar
  //      requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
  //      hideStatusBar()
 //       setStatusBarColor(R.color.black)

        btnAddVehicle = view.findViewById(R.id.btn_addVehicle)
        imgCarWash = view.findViewById(R.id.img_car_wash)
        imgOilChange = view.findViewById(R.id.img_oil_change)
        imgCarRepair = view.findViewById(R.id.img_car_repair)

        recyclerView = view.findViewById(R.id.rv_CarBrands)
        setupRecyclerView()


        val tvTopBrand: TextView = view.findViewById(R.id.tv_top_brand)

        val zoomInInterpolator = DecelerateInterpolator() // Slow motion zoom in
        val zoomOutInterpolator = AccelerateInterpolator() // Slow motion zoom out

        tvTopBrand.animate()
            .scaleX(1.5f)
            .scaleY(1.5f)
            .setDuration(4000) // Change the duration for slow motion
            .setInterpolator(zoomInInterpolator)
            .withEndAction {
                tvTopBrand.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(4000) // Change the duration for slow motion
                    .setInterpolator(zoomOutInterpolator)
                    .start()
            }
            .start()




        val leftIcon = view.findViewById<ImageButton>(R.id.icn_left)
        val rightIcon = view.findViewById<ImageButton>(R.id.icn_right)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val handler = Handler(Looper.getMainLooper())
        val autoScrollRunnable = object : Runnable {
            override fun run() {
                val nextItemPosition = (layoutManager.findFirstVisibleItemPosition() + 2) % layoutManager.itemCount
                recyclerView.smoothScrollToPosition(nextItemPosition)
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(autoScrollRunnable, 3000)


        leftIcon.setOnClickListener {
            recyclerView.smoothScrollBy(-SCROLL_SPEED, 0)
        }

        rightIcon.setOnClickListener {
            recyclerView.smoothScrollBy(SCROLL_SPEED, 0)
        }


        imgCarWash.setOnClickListener {
            startActivity(Intent(requireContext(), CarWashActivity::class.java))
        }
        imgOilChange.setOnClickListener {
            startActivity(Intent(requireContext(), OilChangeActivity::class.java))
        }
        imgCarRepair.setOnClickListener {
            startActivity(Intent(requireContext(), CarRepairingActivity::class.java))
        }

        btnAddVehicle.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        val viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)

        viewFlipper.setInAnimation(requireContext(), android.R.anim.slide_in_left)
        viewFlipper.setOutAnimation(requireContext(), android.R.anim.slide_out_right)
        viewFlipper.isAutoStart = true
        viewFlipper.flipInterval = 3000
        viewFlipper.setOnTouchListener { _, _ -> true }

        return view
    }

    private fun hideStatusBar() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun setStatusBarColor(colorResId: Int) {
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun setupRecyclerView() {
        carBrandsList = generateCarBrandsList()

        vehicleCategoryAdapter = VehicleCategoryAdapter(carBrandsList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = vehicleCategoryAdapter
    }

    private fun generateCarBrandsList(): ArrayList<CarBrand> {
        // Create a sample list of car brands
        val brandsList = ArrayList<CarBrand>()
        brandsList.add(CarBrand(getString(R.string.brand_lamborghini), R.drawable.img_10))
        brandsList.add(CarBrand(getString(R.string.brand_aston_martin), R.drawable.img_11))
        brandsList.add(CarBrand(getString(R.string.brand_ferrari), R.drawable.img_12))
        brandsList.add(CarBrand(getString(R.string.brand_maserati), R.drawable.img_13))
        brandsList.add(CarBrand(getString(R.string.brand_mercedes_benz), R.drawable.img_14))
        brandsList.add(CarBrand(getString(R.string.brand_ford), R.drawable.img_15))
        brandsList.add(CarBrand(getString(R.string.brand_bugatti), R.drawable.img_16))
        brandsList.add(CarBrand(getString(R.string.brand_mclaren), R.drawable.img_17))
        brandsList.add(CarBrand(getString(R.string.brand_rolls_royce), R.drawable.img_18))
        brandsList.add(CarBrand(getString(R.string.brand_bmw), R.drawable.img_7))
        brandsList.add(CarBrand(getString(R.string.brand_nissan), R.drawable.img_8))
        brandsList.add(CarBrand(getString(R.string.brand_lexus), R.drawable.img_9))
        brandsList.add(CarBrand(getString(R.string.brand_lamborghini), R.drawable.img_10))
        brandsList.add(CarBrand(getString(R.string.brand_aston_martin), R.drawable.img_11))
        // Add more brands as needed
        return brandsList
    }

    private fun CarBrand(brandName: String): CarBrand {
        return CarBrand(brandName, R.drawable.brand_img10)
    }
}
