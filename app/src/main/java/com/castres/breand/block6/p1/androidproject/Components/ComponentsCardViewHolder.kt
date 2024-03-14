package com.castres.breand.block6.p1.androidproject.Components

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.castres.breand.block6.p1.androidproject.databinding.ComponentsCardcellBinding

class ComponentsCardViewHolder (
    private val componentsCardcellBinding: ComponentsCardcellBinding,
    private val clickListener: ComponentsClickListener
): RecyclerView.ViewHolder(componentsCardcellBinding.root)
{
    fun bindComponentsItems(componentsItems: ComponentsItems){

        // Load the image using Glide
        Glide.with(componentsCardcellBinding.root)
            .load(componentsItems.image)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    componentsCardcellBinding.componentsCover.setImageDrawable(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Optional: You can handle what happens when the image load is cleared
                }
            })

        componentsCardcellBinding.componentsItemName.text = componentsItems.prod_name
        componentsCardcellBinding.componentsPrice.text = componentsItems.price.toString()

        componentsCardcellBinding.componentsCV.setOnClickListener {
            clickListener.onClick(componentsItems)
        }
    }
}
