package com.example.studentapi.ui.registration_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapi.api.ApiService
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.di.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var api: ApiService

    private val _registerValidateMLD = MutableLiveData<Boolean>()
    val registerValidateMLD: LiveData<Boolean> = _registerValidateMLD

    init {
        application.appComponent.inject(this)
    }

    fun registerStudent(studentData: StudentModel) {
        viewModelScope.launch {
            try {
                api.registerStudent(studentData)
                _registerValidateMLD.postValue(true)
            } catch (e: Exception) {
                _registerValidateMLD.postValue(false)
            }
        }
    }

}