package com.castres.breand.block6.p1.androidproject.Components

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.castres.breand.block6.p1.androidproject.AddToCartActivity
import com.castres.breand.block6.p1.androidproject.RetrofitInstance
import com.castres.breand.block6.p1.androidproject.data.model.modeling.API
import com.castres.breand.block6.p1.androidproject.databinding.ActivityComponentsDetailBinding
import com.castres.breand.block6.p1.androidproject.dataclass.ItemRequest
import kotlinx.coroutines.launch

class ComponentsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComponentsDetailBinding
    private lateinit var api: API
    private lateinit var component: ComponentsDetailItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize API instance using RetrofitInstance
        api = RetrofitInstance.getComp(this)

        // Retrieve the component ID from the intent extras

        // Fetch component details using the ID
        fetchComponentDetails()
    }

    private fun fetchComponentDetails() {
        lifecycleScope.launch {
            try {
                // Make the network request to fetch component details by ID
                val response = api.getComponentDetails()
                if (response.isSuccessful) {
                    val component = response.body()
                    component?.let {
                        // Display component details
                       // displayComponentDetails(components)
                    }
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle failure
            }
        }
    }

    private fun displayComponentDetails(component: ComponentsDetailItems) {
        this.component = component
// Load the image using Glide
        Glide.with(binding.root)
            .load(component.image) // Assuming component.image is a URL or a string representing an image resource
            .into(binding.componentsDetailCover)

        binding.componentsDetailItemName.text = component.prod_name
        binding.componentsDetailPrice.text = component.price.toString()
        binding.componentsDetailDescription.text = component.description
        binding.componentsDetailADC.setImageResource(component.componentsAddToCart)

        // Redirects to add to cart activity
        binding.componentsDetailADC.setOnClickListener {
            addToCart(component)
        }
    }

    private fun addToCart(component: ComponentsDetailItems) {
        val itemRequest = ItemRequest(
            prod_name = component.prod_name,
            description = component.description,
            price = component.price,
            image = component.image, // Assuming component.image is a URL or a string representing an image resource
            category = component.category,
            id = component.id,
            componentsAddToCart = component.componentsAddToCart
        )

        lifecycleScope.launch {
            try {
                val response = api.addToCart(itemRequest)
                if (response.isSuccessful) {
                    redirectToCart()
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle failure
            }
        }
    }


    fun redirectToCart() {
        val intent = Intent(this, AddToCartActivity::class.java)
        startActivity(intent)
    }
}
