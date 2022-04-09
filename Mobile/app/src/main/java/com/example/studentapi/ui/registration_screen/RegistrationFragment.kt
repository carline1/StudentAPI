package com.example.studentapi.ui.registration_screen

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
import com.example.studentapi.common.popBackStack
import com.example.studentapi.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
    }

    private fun setUpUI() {
        binding.toolbar.setNavigationOnClickListener {
            popBackStack()
        }
        binding.actionButton.setOnClickListener {
            if (validateFields()) {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                val firstname = binding.firstname.text.toString()
                val lastname = binding.lastname.text.toString()
                val group = binding.group.text.toString()
                val studentData = StudentModel(
                    email = email,
                    password = password,
                    firstname = firstname,
                    lastname = lastname,
                    group = group
                )
                viewModel.registerStudent(studentData)
            }
        }
    }

    private fun validateFields(): Boolean {
        val isEmailValid = binding.email.text.isValidEmail()
        val isPasswordValid = binding.password.text.isNotBlank()
        val isFirstnameValid = binding.firstname.text.isNotBlank()
        val isLastnameValid = binding.lastname.text.isNotBlank()
        val isGroupValid = binding.group.text.isNotBlank()

        if (!isEmailValid) {
            binding.email.error = getString(R.string.email_not_valid)
        }
        if (!isPasswordValid) {
            binding.password.error = getString(R.string.edit_text_valid)
        }
        if (!isFirstnameValid) {
            binding.firstname.error = getString(R.string.edit_text_valid)
        }
        if (!isLastnameValid) {
            binding.lastname.error = getString(R.string.edit_text_valid)
        }
        if (!isGroupValid) {
            binding.group.error = getString(R.string.edit_text_valid)
        }

        return isEmailValid && isPasswordValid && isFirstnameValid && isLastnameValid && isGroupValid
    }

    private fun setObservers() {
        viewModel.registerValidateMLD.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    context,
                    getString(R.string.register_success),
                    Toast.LENGTH_SHORT
                ).show()
                popBackStack()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.email_duplicate),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}