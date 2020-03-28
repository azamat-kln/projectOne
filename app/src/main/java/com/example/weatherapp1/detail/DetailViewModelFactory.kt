package com.example.weatherapp1.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp1.domain.Model

class DetailViewModelFactory(private val model: Model) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)) {
            return DetailFragmentViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}