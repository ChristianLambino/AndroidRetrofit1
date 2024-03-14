package com.castres.breand.block6.p1.androidproject.Components

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.castres.breand.block6.p1.androidproject.Components.ComponentRepository
import com.castres.breand.block6.p1.androidproject.Components.ComponentsItems
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComponentsViewModel(private val componentRepository: ComponentRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val componentsList = MutableLiveData<List<ComponentsItems>>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }



    fun getComponent() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = componentRepository.getComponent()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        componentsList.postValue(listOf(items))
                    } else {
                        onError("Empty response")
                    }
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }


    fun onError(message: String) {
        // Update LiveData from the main thread
        viewModelScope.launch {
            errorMessage.value = message
            loading.value = false
        }
    }



    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}
