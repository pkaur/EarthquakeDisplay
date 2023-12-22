package com.puneetkaur.testapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puneetkaur.testapp.model.Earthquakes
import com.puneetkaur.testapp.repository.EarcthquakesRepository
import com.puneetkaur.testapp.utils.Response
import kotlinx.coroutines.launch

class EarthquakesViewModel(val repository: EarcthquakesRepository): ViewModel() {

    private val _earthquakes = MutableLiveData<Response<Earthquakes>>()
    val earthquakes: LiveData<Response<Earthquakes>> = _earthquakes

    init {
        getEarthQuakes()
    }

    fun getEarthQuakes(){

        viewModelScope.launch{
           val earthquakes = repository.getEarthquakes()

            if(earthquakes.isSuccessful) {
                val result = earthquakes.body()

                result?.let {
                    _earthquakes.postValue(Response.Success(result))  }
            } else {
                _earthquakes.postValue(Response.Error("API failed to fetch data"))
            }
        }
    }
}