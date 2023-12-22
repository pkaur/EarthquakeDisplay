package com.puneetkaur.testapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://api.geonames.org/"
class RetrofitHelper {

    companion object{

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            getInstance().create(EarthquakesApi::class.java)
        }

    }
}