package com.castres.breand.block6.p1.androidproject.Components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.castres.breand.block6.p1.androidproject.R

class ComponentsAdapter(private var componentsList: MutableList<ComponentsItems> = mutableListOf(), private val clickListener: ComponentsClickListener? = null) :
    RecyclerView.Adapter<ComponentsAdapter.ComponentsViewHolder>() {

    class ComponentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val componentsImageView: ImageView = itemView.findViewById(R.id.componentsImageView)
        val componentsNameTv: TextView = itemView.findViewById(R.id.componentsNameTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.components, parent, false)
        return ComponentsViewHolder(view)
    }

    fun setItems(items: List<ComponentsItems>) {
        componentsList.clear() // Clear the existing list
        componentsList.addAll(items) // Add all new items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return componentsList.size
    }

    override fun onBindViewHolder(holder: ComponentsViewHolder, position: Int) {
        val item = componentsList[position]
        if (!item.image.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(item.image)
                .into(holder.componentsImageView)
        } else {
            // Handle case when image URL is null or empty
            // For example, you can load a placeholder image
            Glide.with(holder.itemView.context)
                .load(R.drawable.csd_logo) // Replace placeholder_image with your actual placeholder image resource
                .into(holder.componentsImageView)
        }
        holder.componentsNameTv.text = item.prod_name

        // Set click listener for the item view
        holder.itemView.setOnClickListener {
            clickListener?.onClick(item)
        }
    }
}
