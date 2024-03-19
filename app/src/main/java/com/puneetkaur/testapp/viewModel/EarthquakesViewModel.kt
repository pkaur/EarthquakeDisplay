package com.puneetkaur.testapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puneetkaur.testapp.model.Earthquakes
import com.puneetkaur.testapp.repository.EarcthquakesRepository
import com.puneetkaur.testapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EarthquakesViewModel(val repository: EarcthquakesRepository): ViewModel() {

    private val _earthquakes = MutableLiveData<Response<Earthquakes>>()
    val earthquakes: LiveData<Response<Earthquakes>> = _earthquakes

    fun getEarthQuakes(){
        viewModelScope.launch{
            try{
            // Emit loading state
            _earthquakes.value = Response.Loading()

            val earthquakes = withContext(Dispatchers.IO){ repository.getEarthquakes()}

            if(earthquakes.isSuccessful) {
                val result = earthquakes.body()

                result?.let {
                    _earthquakes.value = Response.Success(result)  }
            } else {
                _earthquakes.value = Response.Error("API failed to fetch data")
            }
        } catch (e: Exception){
                // Handle exceptions, log or notify accordingly
                _earthquakes.value = Response.Error("An error occurred: ${e.message}")
        }
        }
    }
}