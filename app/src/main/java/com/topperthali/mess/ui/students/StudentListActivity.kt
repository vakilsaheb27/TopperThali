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
                    // Pass the long-click behavior into the adapter
                    adapter = StudentAdapter(studentList) { student ->
                        showRenewDialog(student)
                    }
                    rvStudents.adapter = adapter
                } else {
                    adapter?.updateData(studentList)
                }
            }
        }
    }

    private fun showRenewDialog(student: StudentEntity) {
        AlertDialog.Builder(this)
            .setTitle("Renew Subscription")
            .setMessage("Do you want to recharge ${student.name}'s account with 30 more days?")
            .setPositiveButton("Yes, Renew") { _, _ ->
                renewStudent(student)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun renewStudent(student: StudentEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@StudentListActivity)
            
            // Add 30 to their current credits (just in case they renewed a few days early)
            val updatedStudent = student.copy(creditsRemaining = student.creditsRemaining + 30)
            db.messDao().updateStudent(updatedStudent)
            
            withContext(Dispatchers.Main) {
                Toast.makeText(this@StudentListActivity, "✅ ${student.name} renewed successfully!", Toast.LENGTH_SHORT).show()
                loadStudents() // Refresh the list
            }
        }
    }
}
