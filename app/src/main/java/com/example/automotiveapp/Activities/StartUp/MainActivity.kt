package com.example.automotiveapp.Activities.StartUp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.automotiveapp.Activities.Main.ChauffeurServiceActivity
import com.example.automotiveapp.Activities.Main.ContactUsActivity
import com.example.automotiveapp.Activities.Main.ListingsActivity
import com.example.automotiveapp.Adapter.PagerAdapter.MainPagerAdapter
import com.example.automotiveapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var context: Context = this@MainActivity

    private lateinit var tvSignOut: TextView
    private lateinit var mContext: Context

    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var menuIcon: ImageButton
    private lateinit var expandableMenu: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initBottomNavigation()
        hideSystemUI()

        menuIcon = findViewById(R.id.menu_icon)
        expandableMenu = findViewById(R.id.expandable_menu)



        tvSignOut = findViewById(R.id.tv_SignOut)

        tvSignOut.setOnClickListener {
            openLogoutDialog()
        }


        menuIcon.setOnClickListener {
            toggleSideMenu()
        }

        val tvHome = findViewById<TextView>(R.id.tv_home)
        val tvListings = findViewById<TextView>(R.id.tv_Listings)
        val tvChauffeurService = findViewById<TextView>(R.id.tv_ChauffeurService)
        val tvContactUs = findViewById<TextView>(R.id.tv_ContactUs)

        tvHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tvListings.setOnClickListener {
            val intent = Intent(this, ListingsActivity::class.java)
            startActivity(intent)
        }
        tvChauffeurService.setOnClickListener {
            val intent = Intent(this, ChauffeurServiceActivity::class.java)
            startActivity(intent)
        }
        tvContactUs.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }


    }
    private fun openLogoutDialog() {

        MaterialAlertDialogBuilder(this@MainActivity)
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
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun hideSystemUI() {

        val activity = context as Activity
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            activity.window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }    }

    private fun toggleSideMenu() {

        if (expandableMenu.visibility == View.VISIBLE) {
            expandableMenu.visibility = View.GONE
        } else {
            expandableMenu.visibility = View.VISIBLE
        }
    }

    private fun initViewPager() {

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPager = findViewById(R.id.vp_main)
        viewPager.adapter = mainPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun initBottomNavigation() {

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_buyACar -> viewPager.currentItem = 0
                R.id.menu_rentACar -> viewPager.currentItem = 1
                R.id.menu_favorites -> viewPager.currentItem = 2
                R.id.menu_profile -> viewPager.currentItem = 3
            }
            true
        }
    }
}
