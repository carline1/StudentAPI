package com.example.studentapi.api.models


data class StudentModel(
    val id_stud: Int? = null,
    val email: String? = null,
    val password: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val group: String? = null,
    val date_of_reg: String? = null,
)

