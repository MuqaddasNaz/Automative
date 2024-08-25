package com.example.automotiveapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.automotiveapp.Model.Vehicle
import com.example.automotiveapp.R

class BuyACarAdapter(

    private val context: Context,
    private var carForBuyList: MutableList<Vehicle>,
    private val listener: BuyACarAdapterListener

) : RecyclerView.Adapter<BuyACarAdapter.ViewHolder>() {

    interface BuyACarAdapterListener {
        // Add any necessary methods for interaction
    }

    override fun getItemCount(): Int {
        return carForBuyList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_buy_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = carForBuyList[position]
        holder.bind(item)
    }

    fun submitList(list: MutableList<Vehicle>) {

        carForBuyList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val ivCarImage: ImageView = itemView.findViewById(R.id.iv_carImage)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvModelYear: TextView = itemView.findViewById(R.id.tv_Model_Year)
        private val tvModel: TextView = itemView.findViewById(R.id.tv_model)
        private val tvExteriorColour: TextView = itemView.findViewById(R.id.tv_exterior_colour)
        private val tvCondition: TextView = itemView.findViewById(R.id.tv_condition)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

        init {

            itemView.setOnClickListener(this)
        }

        fun bind(item: Vehicle) {

            tvTitle.text = item.bedrooms
            tvModelYear.text = item.propertyType
            tvExteriorColour.text = item.engineCapacity
            tvModel.text = item.bedrooms
            tvCondition.text = item.areaSize
            tvPrice.text = item.region
            Glide.with(context).load(item.emailAddress).into(ivCarImage)
        }

        override fun onClick(v: View) {

            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                // Handle item click
            }
        }
    }
}
