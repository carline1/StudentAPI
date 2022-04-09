package com.example.studentapi.ui.students_screen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapi.R
import com.example.studentapi.api.ApiService
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.di.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudentsViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var api: ApiService

    private val _studentsDataMLD = MutableLiveData<List<StudentModel>>()
    val studentsDataMLD: LiveData<List<StudentModel>> = _studentsDataMLD

    init {
        application.appComponent.inject(this)
    }

    fun getStudents() {
        viewModelScope.launch {
            try {
                _studentsDataMLD.postValue(api.getStudents())
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        getApplication<Application>().getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}