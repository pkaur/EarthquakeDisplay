package com.puneetkaur.testapp

import EarthquakesAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.puneetkaur.testapp.databinding.ActivityMainBinding
import com.puneetkaur.testapp.model.Earthquake
import com.puneetkaur.testapp.repository.EarcthquakesRepository
import com.puneetkaur.testapp.utils.Response
import com.puneetkaur.testapp.viewModel.EarthquakesViewModel
import com.puneetkaur.testapp.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: EarthquakesViewModel
    lateinit var adapter: EarthquakesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repo = EarcthquakesRepository()
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repo)).get(EarthquakesViewModel::class.java)

        viewModel.earthquakes.observe(this, Observer { response ->
            when (response) {
                is Response.Loading -> { // display progress bar

                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is Response.Success -> {
                    // Update UI with earthquakes
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    // Update UI with earthquakes
                    val adapter = EarthquakesAdapter(response.data?.earthquakes!!)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                    binding.recyclerView.adapter = adapter

                    adapter.setOnClickListener { clickedItem ->
                        navigateToFragment(clickedItem)
                    }
                }
                is Response.Error -> {
                    // display retry button and error message
                    handleErrorState(response.message)
                }
            }

        })


        // Add OnBackStackChangedListener to update visibility on back stack changes
        supportFragmentManager.addOnBackStackChangedListener {
            updateVisibility()
        }


        // Trigger the API call when the activity is created or as needed
        viewModel.getEarthQuakes()
    }

    private fun handleErrorState(message: String?) {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            showError(message)
    }

    private fun showError(errorMessage: String?) {
        // You can show an error message in a TextView or any other UI element
        binding.layout.errorMessage.text = errorMessage

        binding.layout.retry.visibility = View.VISIBLE

    }

    private fun updateVisibility() {
        // Check if the back stack is empty
        val isBackStackEmpty = supportFragmentManager.backStackEntryCount == 0

        // Set the visibility based on the back stack status
        binding.recyclerView.visibility = if (isBackStackEmpty) View.VISIBLE else View.GONE
    }

    private fun navigateToFragment(clickedItem: Earthquake) {

        val bundle = Bundle()
        bundle.putParcelable("clickedItem", clickedItem)

        // Log the contents of the Bundle
        Log.d("NavigateToFragment", "Bundle Contents: $bundle")


        // Create a new instance of BlankFragment and set the arguments
        val fragment = BlankFragment()
        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}