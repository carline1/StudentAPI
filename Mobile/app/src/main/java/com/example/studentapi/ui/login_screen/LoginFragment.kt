package com.example.studentapi.ui.login_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.studentapi.R
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.common.isValidEmail
import com.example.studentapi.common.navigateTo
import com.example.studentapi.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
    }

    private fun setUpUI() {
        binding.actionButton.setOnClickListener {
            if (validateFields()) {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                val studentData = StudentModel(email = email, password = password)
                viewModel.validateLogin(studentData)
            }
        }
        binding.register.setOnClickListener {
            navigateTo(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun validateFields(): Boolean {
        val isEmailValid = binding.email.text.isValidEmail()
        val isPasswordValid = binding.password.text.isNotEmpty()

        if (!isEmailValid) {
            binding.email.error = getString(R.string.email_not_valid)
        }
        if (!isPasswordValid) {
            binding.password.error = getString(R.string.edit_text_valid)
        }

        return isEmailValid && isPasswordValid
    }

    private fun setObservers() {
        viewModel.loginValidateMLD.observe(viewLifecycleOwner) {
            if (it) {
                navigateTo(R.id.action_loginFragment_to_studentsFragment)
            } else {
                Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }

}