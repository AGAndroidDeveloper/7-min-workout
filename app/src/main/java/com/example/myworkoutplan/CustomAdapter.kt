package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myworkoutplan.databinding.DataFormatBinding

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /**
     * Inflates the item view which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataFormatBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = items[position]

        holder.tvItemobj.text = model.getId().toString()
        // TODO(Step 4 : Updating the current item and the completed item in the UI and changing the background and text color according to it..)
        // START
        // Updating the background and text color according to the flags that is in the list.
        // A link to set text color programmatically and same way we can set the drawable background also instead of color.
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

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(binding:DataFormatBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val tvItemobj = binding.tvitem
    }
}
