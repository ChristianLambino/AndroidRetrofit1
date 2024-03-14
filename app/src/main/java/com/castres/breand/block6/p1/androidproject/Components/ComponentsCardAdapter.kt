package com.castres.breand.block6.p1.androidproject.Components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castres.breand.block6.p1.androidproject.databinding.ComponentsCardcellBinding

class ComponentsCardAdapter (
    private val componentsItems : List<ComponentsItems>,
    private val clickListener: ComponentsClickListener
)
    :RecyclerView.Adapter<ComponentsCardViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentsCardViewHolder
    {
        val from = LayoutInflater.from(parent.context)
        val binding = ComponentsCardcellBinding.inflate(from, parent, false)
        return ComponentsCardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ComponentsCardViewHolder, position: Int)
    {
        holder.bindComponentsItems(componentsItems[position])
    }
    override fun getItemCount(): Int =componentsItems.size

}