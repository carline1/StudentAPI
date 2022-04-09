package com.example.studentapi.ui.login_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapi.api.ApiService
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.di.appComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var api: ApiService

    private var coroutineContext = CoroutineScope(Dispatchers.IO)

    private val _loginValidateMLD = MutableLiveData<Boolean>()
    val loginValidateMLD: LiveData<Boolean> = _loginValidateMLD

    init {
        application.appComponent.inject(this)
    }

    fun validateLogin(studentData: StudentModel) {
        viewModelScope.launch {
            try {
                api.validateLogin(studentData)
                _loginValidateMLD.postValue(true)
            } catch (e: Exception) {
                _loginValidateMLD.postValue(false)
            }
        }
    }

}