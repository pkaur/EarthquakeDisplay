package com.puneetkaur.testapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puneetkaur.testapp.repository.EarcthquakesRepository

class ViewModelFactory(val repoaitory: EarcthquakesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EarthquakesViewModel(repoaitory) as T
    }
}