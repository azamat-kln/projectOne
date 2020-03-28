package com.example.weatherapp1.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp1.database.getDatabase
import com.example.weatherapp1.domain.Model
import com.example.weatherapp1.network.Mocky
import com.example.weatherapp1.repository.Repository
import kotlinx.coroutines.*
import java.io.IOException

class BlankFragmentViewModel(application: Application) : ViewModel() {

    private val viewmodelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    private val repository = Repository(getDatabase(application))

    val mockyData = repository.livedataMocky

    private val _someGender = MutableLiveData<GenderConst>()
    val someGender: LiveData<GenderConst>
        get() = _someGender

    private val _selectedModel = MutableLiveData<Model>()
    val selectedModel: LiveData<Model>
        get() = _selectedModel

    private val _imageState = MutableLiveData<ImageStatus>()
    val statusImage: LiveData<ImageStatus>
        get() = _imageState

    init {
        getUserProperties()
    }

    private fun getUserProperties() {
        uiScope.launch {
            try {
                _imageState.value = ImageStatus.LOADING
                repository.getMockyData()
                _imageState.value = ImageStatus.DONE
            } catch (networkError: IOException) {
                _imageState.value = ImageStatus.ERROR
            }

        }
    }

    fun onMaleMenuSelected() {
        _someGender.value = GenderConst.MALE
    }

    fun onFemaleMenuSelected() {
        _someGender.value = GenderConst.FEMALE
    }

    fun onAllMenuSelected() {
        _someGender.value = GenderConst.ALL
    }

    fun onTextClicked(model: Model) {
        _selectedModel.value = model
    }

    fun completeNavigation() {
        _selectedModel.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }

}

enum class GenderConst {
    ALL, MALE, FEMALE
}

enum class ImageStatus {
    LOADING, ERROR, DONE
}