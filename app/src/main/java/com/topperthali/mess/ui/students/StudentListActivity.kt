package com.topperthali.mess.ui.students

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentListActivity : AppCompatActivity() {

    private lateinit var rvStudents: RecyclerView
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        rvStudents = findViewById(R.id.rvStudents)
        rvStudents.layoutManager = LinearLayoutManager(this)

        val fabAddStudent = findViewById<FloatingActionButton>(R.id.fabAddStudent)
        fabAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudents()
    }

    private fun loadStudents() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentListActivity)
            val studentList = db.messDao().getAllStudents()

            withContext(Dispatchers.Main) {
                if (adapter == null) {
                    adapter = StudentAdapter(studentList) { student ->
                        showOptionsDialog(student) // Show menu instead of direct renew
                    }
                    rvStudents.adapter = adapter
                } else {
                    adapter?.updateData(studentList)
                }
            }
        }
    }

    private fun showOptionsDialog(student: StudentEntity) {
        val options = arrayOf("🔄 Renew Subscription (+30 Days)", "📷 View QR Code", "❌ Delete Student")
        AlertDialog.Builder(this)
            .setTitle("${student.name} Options")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> confirmRenew(student)
                    1 -> showQrCode(student)
                    2 -> confirmDelete(student)
                }
            }
            .show()
    }

    private fun showQrCode(student: StudentEntity) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_qr_code, null)
        val tvName = dialogView.findViewById<android.widget.TextView>(R.id.tvStudentNameDialog)
        val ivQr = dialogView.findViewById<android.widget.ImageView>(R.id.ivQrCodeDialog)
        val btnDone = dialogView.findViewById<android.widget.Button>(R.id.btnDoneDialog)

        tvName.text = student.name

        try {
            val qrBitmap = com.topperthali.mess.utils.QrGenerator.generateQrCode(student.qrCode)
            if (qrBitmap != null) {
                ivQr.setImageBitmap(qrBitmap)
            } else {
                Toast.makeText(this, "Failed to generate QR Code", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error generating QR Code: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnDone.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun confirmRenew(student: StudentEntity) {
        AlertDialog.Builder(this)
            .setTitle("Renew Subscription")
            .setMessage("Recharge ${student.name}'s account with 30 more days?")
            .setPositiveButton("Yes") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = MessDatabase.getDatabase(this@StudentListActivity)
                    val updatedStudent = student.copy(creditsRemaining = student.creditsRemaining + 30)
                    db.messDao().updateStudent(updatedStudent)
                    
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@StudentListActivity, "✅ ${student.name} renewed!", Toast.LENGTH_SHORT).show()
                        loadStudents()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmDelete(student: StudentEntity) {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to permanently delete ${student.name}? This cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = MessDatabase.getDatabase(this@StudentListActivity)
                    db.messDao().deleteStudent(student)
                    
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@StudentListActivity, "🗑️ ${student.name} deleted.", Toast.LENGTH_SHORT).show()
                        loadStudents()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
