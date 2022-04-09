package com.example.studentapi.ui.student_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.studentapi.R
import com.example.studentapi.api.models.AttendanceModel
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.common.isValidEmail
import com.example.studentapi.common.navigateTo
import com.example.studentapi.common.parseDate
import com.example.studentapi.common.popBackStack
import com.example.studentapi.databinding.FragmentStudentInfoBinding


class StudentInfoFragment : Fragment() {

    private var _binding: FragmentStudentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentInfoViewModel by viewModels()
    private val args: StudentInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentStudentInfoBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
        viewModel.getStudentInfoById(args.id)
    }

    private fun setUpUI() {
        binding.toolbar.setNavigationOnClickListener {
            popBackStack()
        }
        binding.actionButtonSave.setOnClickListener {
            if (validateFields()) {
                viewModel.updateStudentInfoById(
                    args.id,
                    StudentModel(
                        id_stud = null,
                        email = binding.email.text.toString(),
                        password = null,
                        firstname = binding.firstname.text.toString(),
                        lastname = binding.lastname.text.toString(),
                        group = binding.group.text.toString(),
                        date_of_reg = null
                    )
                )
            }
        }
        binding.actionButtonCheck.setOnClickListener {
            viewModel.addAttendance(AttendanceModel(args.id))
        }
        binding.actionButtonSeeChecks.setOnClickListener {
            val action = StudentInfoFragmentDirections
                .actionStudentInfoFragmentToAttendancesFragment(args.id)
            navigateTo(action)
        }
    }

    private fun validateFields(): Boolean {
        val isEmailValid = binding.email.text.isValidEmail()
        val isFirstnameValid = binding.firstname.text.isNotBlank()
        val isLastnameValid = binding.lastname.text.isNotBlank()
        val isGroupValid = binding.group.text.isNotBlank()

        if (!isEmailValid) {
            binding.email.error = getString(R.string.email_not_valid)
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

        return isEmailValid && isFirstnameValid && isLastnameValid && isGroupValid
    }

    private fun setObservers() {
        viewModel.studentInfoMLD.observe(viewLifecycleOwner) {
            binding.firstname.setText(it.firstname)
            binding.lastname.setText(it.lastname)
            binding.email.setText(it.email)
            binding.group.setText(it.group)
            binding.email.setText(it.email)
            binding.date.setText(it.date_of_reg?.parseDate())
        }
    }

}