package com.example.bitfit2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FoodAdapter(private val context: Context, private val foodList: List<DisplayFood>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)
    }

    override fun getItemCount() = foodList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodNameTextView = itemView.findViewById<TextView>(R.id.foodNameTextView)
        private val numberOfCalories = itemView.findViewById<TextView>(R.id.numberOfCalories)

        fun bind(food: DisplayFood) {
            foodNameTextView.text = food.foodName
            numberOfCalories.text = food.caloriesNumber
        }
    }

}

