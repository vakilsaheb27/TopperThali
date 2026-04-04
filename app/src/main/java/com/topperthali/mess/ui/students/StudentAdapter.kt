package com.topperthali.mess.ui.students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.topperthali.mess.R
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.utils.WhatsAppHelper

class StudentAdapter(private val studentList: List<StudentEntity>) : 
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvPhone: TextView = itemView.findViewById(R.id.tvItemPhone)
        val tvDays: TextView = itemView.findViewById(R.id.tvItemDays)
        val btnWhatsApp: ImageView = itemView.findViewById(R.id.btnWhatsApp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_card, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvName.text = student.name
        holder.tvPhone.text = student.phone
        holder.tvDays.text = student.creditsRemaining.toString()

        // Turn text red if 3 or fewer days remaining
        if (student.creditsRemaining <= 3) {
            holder.tvDays.setTextColor(android.graphics.Color.RED)
        } else {
            holder.tvDays.setTextColor(android.graphics.Color.parseColor("#0D47A1"))
        }

        // Handle WhatsApp Click
        holder.btnWhatsApp.setOnClickListener {
            WhatsAppHelper.sendReminder(
                context = holder.itemView.context,
                phone = student.phone,
                studentName = student.name,
                daysLeft = student.creditsRemaining
            )
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    }
