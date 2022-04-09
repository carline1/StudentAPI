package com.example.studentapi.ui.attendances_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studentapi.common.popBackStack
import com.example.studentapi.databinding.FragmentAttendancesBinding


class AttendancesFragment : Fragment() {

    private var _binding: FragmentAttendancesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AttendancesViewModel by viewModels()

    private val adapter = AttendancesAdapter(listOf())
    private val args: AttendancesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentAttendancesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
        viewModel.getAttendancesById(args.id)
    }

    private fun setUpUI() {
        binding.studentsRecycler.adapter = adapter
        binding.studentsRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.toolbar.setNavigationOnClickListener {
            popBackStack()
        }
    }

    private fun setObservers() {
        viewModel.attendancesDataMLD.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
    }

}