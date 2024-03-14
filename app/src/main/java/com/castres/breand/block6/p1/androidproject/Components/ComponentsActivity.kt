package com.castres.breand.block6.p1.androidproject.Components

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.castres.breand.block6.p1.androidproject.RetrofitInstance
import com.castres.breand.block6.p1.androidproject.data.model.modeling.API
import com.castres.breand.block6.p1.androidproject.databinding.ActivityComponentsBinding
import kotlinx.coroutines.launch

class ComponentsActivity : AppCompatActivity(), ComponentsClickListener {
    private lateinit var binding: ActivityComponentsBinding
    private lateinit var api: API
    private val componentsList: MutableLiveData<List<ComponentsItems>> = MutableLiveData()
    private lateinit var componentsAdapter: ComponentsAdapter
    private lateinit var viewModel: ComponentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the API
        api = RetrofitInstance.FetchComp(this)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, ComponentViewModelFactory(repository = ComponentRepository(api))).get(
            ComponentsViewModel::class.java)

        // Create a new mutable list and assign it to componentsList.value
        val componentsList: MutableLiveData<MutableList<ComponentsItems>> = MutableLiveData()

        // Initialize ComponentsAdapter with an empty list
        componentsAdapter = ComponentsAdapter(componentsList.value ?: mutableListOf(), this)

        // Set up RecyclerView
        binding.componentsRV.layoutManager = GridLayoutManager(this, 3)
        binding.componentsRV.adapter = componentsAdapter

        // Observe ViewModel
        observeViewModel()

        // Populate the componentsList
        fetchComponents()

        val repository = ComponentRepository(api)
        viewModel = ViewModelProvider(this, ComponentViewModelFactory(repository))
            .get(ComponentsViewModel::class.java)

        viewModel.getComponent()
    }


    override fun onClick(componentsItems: ComponentsItems) {
        val intent = Intent(applicationContext, ComponentsDetailActivity::class.java)
        intent.putExtra(COMPONENTS_ID_EXTRA, componentsItems.id ?: -1) // Pass -1 if id is null
        startActivity(intent)
    }

    private fun fetchComponents() {
        lifecycleScope.launch {
            try {
                // Make the network request to fetch components
                val response = api.getComponent()
                if (response.isSuccessful) {
                    val responseData = response.body() as Map<String, Any>
                    responseData["GPU"]?.let {
                        // Update LiveData with the fetched list
                        componentsList.value = it as List<ComponentsItems>
                    }
                } else {
                    // Handle unsuccessful response
                    val errorMessage = "Error: ${response.message()}"
                    viewModel.onError(errorMessage)
                }
            } catch (e: Exception) {
                // Handle failure
                val errorMessage = "Error: ${e.localizedMessage}"
                viewModel.onError(errorMessage)
            }
        }
    }


    private fun observeViewModel() {
        viewModel.componentsList.observe(this, Observer {
            // Update adapter with new data
            componentsAdapter.setItems(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
    }
}






