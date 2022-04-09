package com.example.studentapi.ui.students_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapi.api.models.StudentModel
import com.example.studentapi.common.navigateTo
import com.example.studentapi.databinding.StudentViewHolderBinding

class StudentsAdapter(
    var items: List<StudentModel> = listOf()
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            StudentViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(
        private val binding: StudentViewHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StudentModel) {
            val fio = item.firstname + " " + item.lastname
            binding.fio.text = fio
            binding.email.text = item.email
            binding.group.text = item.group

            binding.root.setOnClickListener {
                item.id_stud?.let { id ->
                    val action = StudentsFragmentDirections
                        .actionStudentsFragmentToStudentInfoFragment(id)
                    binding.root.navigateTo(action)
                }
            }
        }
    }

}