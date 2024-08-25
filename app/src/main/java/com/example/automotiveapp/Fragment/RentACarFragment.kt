package com.example.automotiveapp.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.automotiveapp.Adapter.BuyACarAdapter
import com.example.automotiveapp.Adapter.CarForRentAdapter
import com.example.automotiveapp.Model.CarForRentItem
import com.example.automotiveapp.Model.Vehicle
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.SharedPref
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RentACarFragment : Fragment(), BuyACarAdapter.BuyACarAdapterListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BuyACarAdapter
    private var carForBuyList = ArrayList<Vehicle>()

    private lateinit var swHome: SwipeRefreshLayout
    private lateinit var sh: SharedPref
    private lateinit var mContext: Context

    private var ref = FirebaseDatabase.getInstance().getReference("vehicles")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rent_a_car, container, false)
        recyclerView = view.findViewById(R.id.rv_carsForRent)
        setupRecyclerView()

        val searchBar = view.findViewById<EditText>(R.id.et_searchBar)

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                performSearch(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        sh = SharedPref(mContext)
        swHome = view.findViewById(R.id.sw_home)

        swHome.setOnRefreshListener {
            getData()
        }

        getData()
        return view
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        adapter = BuyACarAdapter(mContext, carForBuyList, this)
        recyclerView.adapter = adapter
    }

    private fun getData() {
        swHome.isRefreshing = true
        carForBuyList.clear()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val vehicle = snap.getValue(Vehicle::class.java)
                    vehicle?.let {
                        carForBuyList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
                swHome.isRefreshing = false
            }

            override fun onCancelled(error: DatabaseError) {
                swHome.isRefreshing = false
                Toast.makeText(mContext, "Failed to retrieve data.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onLikeButtonClick(view: View, position: Int) {
        // Handle like button click
    }

    private fun performSearch(query: String) {

        val filterList = carForBuyList.filter { vehicle ->

            vehicle.name.lowercase().contains(query.lowercase())
        }

        adapter.submitList(filterList as MutableList<Vehicle>)
    }

    private fun hideStatusBar() {

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun setStatusBarColor(colorResId: Int) {

        activity?.window?.statusBarColor = ContextCompat.getColor(mContext, colorResId)
    }
}
