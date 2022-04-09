package com.example.studentapi.ui.attendances_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapi.api.models.AttendanceModel
import com.example.studentapi.common.parseDate
import com.example.studentapi.databinding.AttendanceViewHolderBinding

class AttendancesAdapter(
    var items: List<AttendanceModel> = listOf()
) : RecyclerView.Adapter<AttendancesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AttendancesAdapter.ViewHolder(
            AttendanceViewHolderBinding.inflate(
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
        private val binding: AttendanceViewHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AttendanceModel) {
            val date = item.date_of_check?.parseDate()
            binding.date.text = date
        }
    }

}