package com.topperthali.mess.ui.students

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.topperthali.mess.R
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.utils.WhatsAppHelper

class StudentAdapter(
    private var studentList: List<StudentEntity>,
    private val onStudentLongClick: (StudentEntity) -> Unit // NEW: Callback for long press
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

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
        holder.tvPhone.text = student.mobile
        holder.tvDays.text = student.creditsRemaining.toString()

        val context = holder.itemView.context
        val attrToResolve = if (student.creditsRemaining <= 3) {
            com.google.android.material.R.attr.colorError
        } else {
            com.google.android.material.R.attr.colorPrimary
        }
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attrToResolve, typedValue, true)
        holder.tvDays.setTextColor(typedValue.data)

        holder.itemView.contentDescription = "${student.name}, Mobile: ${student.mobile}, ${student.creditsRemaining} days remaining. Tap for options."
        holder.itemView.setOnClickListener {
            onStudentLongClick(student)
        }

        holder.btnWhatsApp.contentDescription = "Send WhatsApp reminder to ${student.name}"
        holder.btnWhatsApp.setOnClickListener {
            WhatsAppHelper.sendReminder(
                context = holder.itemView.context,
                phone = student.mobile,
                studentName = student.name,
                daysLeft = student.creditsRemaining
            )
        }

        // NEW: Detect long press on the whole card
        holder.itemView.setOnLongClickListener {
            onStudentLongClick(student)
            true // Return true to indicate we consumed the long click
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateData(newList: List<StudentEntity>) {
        this.studentList = newList
        notifyDataSetChanged()
    }
}
