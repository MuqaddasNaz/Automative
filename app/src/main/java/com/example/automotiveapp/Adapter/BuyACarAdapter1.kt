package com.example.automotiveapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.automotiveapp.Model.AddVehicle
import com.example.automotiveapp.R

class BuyACarAdapter1(private val context: Context, private val addPropertyItemList: MutableList<AddVehicle>) : RecyclerView.Adapter<BuyACarAdapter1.ViewHolder>()  {


    fun addItem(item: AddVehicle) {
        addPropertyItemList.add(item)
        notifyItemInserted(addPropertyItemList.size - 1)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_rent_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteItem = addPropertyItemList[position]
        holder.tvTitle.text = favoriteItem.title
        holder.tvCondition.text = favoriteItem.condition
        holder.tvExteriorColour.text = favoriteItem.exteriorColour
        holder.tvModelYear.text = favoriteItem.modeYear
        holder.tvModel.text = favoriteItem.model
        holder.image.setImageResource(favoriteItem.imageResource)
    }


    override fun getItemCount(): Int {
        return addPropertyItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvCondition: TextView = itemView.findViewById(R.id.tv_condition)
        val tvExteriorColour: TextView = itemView.findViewById(R.id.tv_exterior_colour)
        val tvModelYear: TextView = itemView.findViewById(R.id.tv_Model_Year)
        val tvModel: TextView = itemView.findViewById(R.id.tv_model)
        val image: ImageView = itemView.findViewById(R.id.iv_img)
    }

    companion object {
        fun createAdapter(context: Context, addPropertyList: MutableList<AddVehicle>): BuyACarAdapter1 {
            return BuyACarAdapter1(context, addPropertyList)
        }
    }
}