package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataList :ArrayList<ExerciseLIst>) :
        RecyclerView.Adapter<Adapter.CustomHolder>() {

        class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {

val llObj :LinearLayout = view.findViewById(R.id.linear_layout_El)
           val dateObj :TextView = view.findViewById(R.id.ExercieName_list)
val idObj :TextView = view.findViewById(R.id.id)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
            val holder = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item_format,parent,false)
            return CustomHolder(holder)
        }
        override fun getItemCount(): Int {
            return dataList.size
        }
        @SuppressLint("ResourceType")
        override fun onBindViewHolder(holder: CustomHolder, position: Int) {
            val context = holder.itemView.context
            val data = dataList[position]
            holder.idObj.text = data.id.toString()
            holder.dateObj.text = data.date

            if (position%2==0){
                holder.llObj.setBackgroundColor(
                    ContextCompat.getColor(context,
                    R.color.my))
            }else{
                holder.llObj.setBackgroundColor(
                    ContextCompat.getColor(context,
                        androidx.appcompat.R.color.material_grey_50))
            }





        }






    }
