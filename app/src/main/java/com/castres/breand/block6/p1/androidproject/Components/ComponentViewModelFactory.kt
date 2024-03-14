package com.castres.breand.block6.p1.androidproject.Components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComponentViewModelFactory(private val repository: ComponentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ComponentsViewModel::class.java)) {
            ComponentsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}

