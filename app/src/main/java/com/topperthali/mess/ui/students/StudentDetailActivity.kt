package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.utils.QrGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var student: StudentEntity
    private var studentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        studentId = intent.getIntExtra("STUDENT_ID", -1)
        if (studentId == -1) {
            Toast.makeText(this, "Error: Student Not Found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        findViewById<MaterialToolbar>(R.id.topAppBarDetail).setNavigationOnClickListener {
            finish()
        }

        loadStudentData()
    }

    private fun loadStudentData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentDetailActivity)
            val currentStudent = db.messDao().getStudentById(studentId)

            if (currentStudent != null) {
                student = currentStudent
                withContext(Dispatchers.Main) {
                    updateUI()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@StudentDetailActivity, "Student data missing.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun updateUI() {
        findViewById<TextView>(R.id.tvDetailName).text = student.name
        findViewById<TextView>(R.id.tvDetailPhone).text = student.phone
        findViewById<TextView>(R.id.tvDetailDaysLeft).text = "${student.creditsRemaining} Days"
        findViewById<ImageView>(R.id.ivDetailQr).setImageBitmap(QrGenerator.generateQrCode(student.qrData))

        val switchHoliday = findViewById<MaterialSwitch>(R.id.switchHoliday)
        switchHoliday.isChecked = student.isOnHoliday
        switchHoliday.setOnCheckedChangeListener { _, isChecked ->
            updateHolidayStatus(isChecked)
        }

        findViewById<MaterialButton>(R.id.btnRenew).setOnClickListener {
            renewSubscription()
        }

        findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener {
            showDeleteConfirmation()
        }
    }

    private fun updateHolidayStatus(isOnHoliday: Boolean) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentDetailActivity)
            val updatedStudent = student.copy(isOnHoliday = isOnHoliday)
            db.messDao().updateStudent(updatedStudent)
            student = updatedStudent
            withContext(Dispatchers.Main) {
                Toast.makeText(this@StudentDetailActivity, "Holiday status updated.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renewSubscription() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentDetailActivity)
            val updatedStudent = student.copy(creditsRemaining = student.creditsRemaining + 30)
            db.messDao().updateStudent(updatedStudent)
            student = updatedStudent
            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.tvDetailDaysLeft).text = "${student.creditsRemaining} Days"
                Toast.makeText(this@StudentDetailActivity, "30 Days Added Successfully!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Delete Student?")
            .setMessage("Are you sure you want to delete ${student.name}'s record? This cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                deleteStudent()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteStudent() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentDetailActivity)
            db.messDao().deleteStudent(student)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@StudentDetailActivity, "Student profile deleted.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
