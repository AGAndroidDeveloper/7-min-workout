package com.example.myworkoutplan


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myworkoutplan.databinding.DataFormatBinding

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataFormatBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = items[position]

        holder.tvItemobj.text = model.getId().toString()
        // https://stackoverflow.com/questions/8472349/how-to-set-text-color-to-a-text-view-programmatically
        when {
            model.getIsSelected() -> {
                holder.tvItemobj.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.white_background_for_exercise_number
                    )
                holder.tvItemobj.setTextColor(Color.parseColor("#212121")) // Parse the color string, and return the corresponding color-int.
            }
            model.getIsCompleted() -> {
                holder.tvItemobj.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.green_background_for_exercise_number)
                holder.tvItemobj.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder. tvItemobj.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.grey_background_for_exercise_number)
                holder.tvItemobj.setTextColor(Color.parseColor("#212121"))
            }
        }
        // END
    }

    override fun getItemCount(): Int {
        return items.size
    }
    
    class ViewHolder(binding:DataFormatBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val tvItemobj = binding.tvitem
    }
}
