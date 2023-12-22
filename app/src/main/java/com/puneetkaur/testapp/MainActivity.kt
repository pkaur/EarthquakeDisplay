package com.puneetkaur.testapp

import EarthquakesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.puneetkaur.testapp.databinding.ActivityMainBinding
import com.puneetkaur.testapp.repository.EarcthquakesRepository
import com.puneetkaur.testapp.viewModel.EarthquakesViewModel
import com.puneetkaur.testapp.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: EarthquakesViewModel
    lateinit var adapter: EarthquakesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val repo = EarcthquakesRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(EarthquakesViewModel::class.java)

        viewModel.earthquakes.observe(this, Observer { earthquakes ->
            // Update UI with earthquakes
            val adapter = EarthquakesAdapter(earthquakes.data?.earthquakes!!)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
           binding.recyclerView.adapter = adapter
        })
    }
}