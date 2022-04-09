package com.example.studentapi.di

import com.example.studentapi.di.modules.NetworkModule
import com.example.studentapi.ui.attendances_screen.AttendancesViewModel
import com.example.studentapi.ui.login_screen.LoginViewModel
import com.example.studentapi.ui.registration_screen.RegistrationViewModel
import com.example.studentapi.ui.student_info.StudentInfoViewModel
import com.example.studentapi.ui.students_screen.StudentsViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(viewModel: StudentsViewModel)
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: RegistrationViewModel)
    fun inject(viewModel: StudentInfoViewModel)
    fun inject(viewModel: AttendancesViewModel)

}