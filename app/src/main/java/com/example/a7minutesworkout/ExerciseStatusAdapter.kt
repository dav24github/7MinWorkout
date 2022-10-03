package com.example.a7minutesworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>,private val context: Context) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvItem : TextView

        init{
            // Define click listener for the ViewHolder's View.
            tvItem = view.findViewById(R.id.tvItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_status, parent, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.tvItem.text = model.getId().toString()

        if(model.getIsSelected()) {
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_thin_color_accent_border
            )
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }else if(model.getIsCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_color_accent_background
            )
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_color_gray_background
            )
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size
}