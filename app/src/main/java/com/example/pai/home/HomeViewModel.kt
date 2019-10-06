package com.example.pai.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pai.network.EventCategory
import com.example.pai.network.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<EventCategory>()
    val property: LiveData<EventCategory>
        get() = _property

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getEventCategories()
    }

    private fun getEventCategories() {

       coroutineScope.launch {
            val getPropertiesDeffered = Service.retrofitService.getProperties()
           try {
               val listResult = getPropertiesDeffered.await()
               if (listResult.isNotEmpty()) {
                   _property.value = listResult[0]
               }
               _response.value =
                   "Success: ${listResult.size} Mars properties retrieved"
           } catch (e: Exception) {
               _response.value = "Failure: ${e.message}"
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}