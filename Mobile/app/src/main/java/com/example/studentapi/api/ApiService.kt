package com.example.studentapi.api

import com.example.studentapi.api.models.AttendanceModel
import com.example.studentapi.api.models.StudentModel
import retrofit2.http.*

interface ApiService {

    @GET("students")
    suspend fun getStudents(): List<StudentModel>

    @GET("students/{id}")
    suspend fun getStudentInfoById(@Path("id") id: Int): StudentModel

    @PUT("students/{id}")
    suspend fun updateStudentInfoById(@Path("id") id: Int, @Body studentData: StudentModel): StudentModel

    @POST("validate_login")
    suspend fun validateLogin(@Body studentData: StudentModel)

    @POST("registration")
    suspend fun registerStudent(@Body studentData: StudentModel)

    @POST("attendance")
    suspend fun addAttendance(@Body attendance: AttendanceModel)

    @GET("attendance/{id}")
    suspend fun getAttendancesById(@Path("id") id: Int): List<AttendanceModel>

}