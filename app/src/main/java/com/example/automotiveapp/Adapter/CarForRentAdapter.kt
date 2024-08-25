package com.example.automotiveapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Model.CarForRentItem
import com.example.automotiveapp.R

class CarForRentAdapter(private val carForRentList: List<CarForRentItem>) : RecyclerView.Adapter<CarForRentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val exteriorColour: TextView = itemView.findViewById(R.id.tv_exterior_colour)
        private val modelYear: TextView = itemView.findViewById(R.id.tv_Model_Year)
        private val price: TextView = itemView.findViewById(R.id.tv_price)
        private val carImage: ImageView = itemView.findViewById(R.id.iv_carImage)

        fun bind(carForRentItem: CarForRentItem) {
            title.text = carForRentItem.name1
            exteriorColour.text = carForRentItem.color1
            modelYear.text = carForRentItem.year1
            price.text = carForRentItem.price1
            carImage.setImageResource(carForRentItem.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_rent_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carForRentItem = carForRentList[position]
        holder.bind(carForRentItem)
    }

    override fun getItemCount(): Int {
        return carForRentList.size
    }
}
