package com.example.automotiveapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Model.CarBrand
import com.example.automotiveapp.R

class VehicleCategoryAdapter(private val carBrandsList: List<CarBrand>) : RecyclerView.Adapter<VehicleCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carBrandsList[position])
    }

    override fun getItemCount(): Int {
        return carBrandsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.iv_imgBrand)
        private val textView: TextView = itemView.findViewById(R.id.tv_brandName)

        fun bind(carBrand: CarBrand) {
            textView.text = carBrand.brandName
            imageView.setImageResource(carBrand.imageResId)
        }
    }
}