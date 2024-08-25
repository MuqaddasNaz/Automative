package com.example.automotiveapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Model.CarsItem
import com.example.automotiveapp.R

class HomeAdapter(private val carDetailsList: List<CarsItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cars_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carDetailsList[position])
    }

    override fun getItemCount(): Int {
        return carDetailsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_brand)
        private val textViewPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val imageViewCar: ImageView = itemView.findViewById(R.id.iv_img)

        fun bind(carItem: CarsItem) {
            textView.text = carItem.brand
            textViewPrice.text = carItem.price
            imageViewCar.setImageResource(carItem.imageResId)
        }
    }

}
