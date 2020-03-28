package com.example.weatherapp1.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp1.domain.Model

class DetailFragmentViewModel(mocky: Model) : ViewModel() {

    private val _mockyProperty = MutableLiveData<Model>()
    val mockyProperty: LiveData<Model>
        get() = _mockyProperty

    init {
        _mockyProperty.value = mocky
    }


}