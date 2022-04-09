package com.example.studentapi.ui.student_info

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapi.R
import com.example.studentapi.api.ApiService
import com.example.studentapi.api.models.AttendanceModel
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.di.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudentInfoViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var api: ApiService

    private val _studentInfoMLD = MutableLiveData<StudentModel>()
    val studentInfoMLD: LiveData<StudentModel> = _studentInfoMLD

    private val _addAttendanceMLD = MutableLiveData<Boolean>()
    val addAttendanceMLD: LiveData<Boolean> = _addAttendanceMLD


    init {
        application.appComponent.inject(this)
    }

    fun getStudentInfoById(id: Int) {
        viewModelScope.launch {
            _studentInfoMLD.postValue(api.getStudentInfoById(id))
        }
    }

    fun updateStudentInfoById(id: Int, studentData: StudentModel) {
        viewModelScope.launch {
            try {
                api.updateStudentInfoById(id, studentData)
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        getApplication<Application>().getString(R.string.update_student_info_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

    fun addAttendance(attendance: AttendanceModel) {
        viewModelScope.launch {
            try {
                api.addAttendance(attendance)
                launch(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        getApplication<Application>().getString(R.string.attendance_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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