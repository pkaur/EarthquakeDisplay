package com.puneetkaur.testapp.repository

import androidx.lifecycle.MutableLiveData
import com.puneetkaur.testapp.api.RetrofitHelper
import com.puneetkaur.testapp.model.Earthquakes
import retrofit2.Response

class EarcthquakesRepository {

    suspend fun getEarthquakes(): Response<Earthquakes> = RetrofitHelper.api.getEarthquakes()
}