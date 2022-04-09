package com.example.studentapi.ui.attendances_screen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapi.R
import com.example.studentapi.api.ApiService
import com.example.studentapi.api.models.AttendanceModel
import com.example.studentapi.di.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AttendancesViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var api: ApiService

    private val _attendancesDataMLD = MutableLiveData<List<AttendanceModel>>()
    val attendancesDataMLD: LiveData<List<AttendanceModel>> = _attendancesDataMLD

    init {
        application.appComponent.inject(this)
    }

    fun getAttendancesById(id: Int) {
        viewModelScope.launch {
            try {
                _attendancesDataMLD.postValue(api.getAttendancesById(id))
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