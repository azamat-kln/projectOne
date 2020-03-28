package com.example.weatherapp1.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlankFragmentViewModel::class.java)) {
            return BlankFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}