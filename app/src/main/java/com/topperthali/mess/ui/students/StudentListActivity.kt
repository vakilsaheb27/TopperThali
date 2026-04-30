package com.topperthali.mess.ui.students

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
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
    private lateinit var llEmptyState: LinearLayout
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener { finish() }

        rvStudents = findViewById(R.id.rvStudents)
        llEmptyState = findViewById(R.id.llEmptyState)
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
                if (studentList.isEmpty()) {
                    rvStudents.visibility = View.GONE
                    llEmptyState.visibility = View.VISIBLE
                } else {
                    rvStudents.visibility = View.VISIBLE
                    llEmptyState.visibility = View.GONE
                }

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
        val options = arrayOf("🔄 Renew Subscription (+30 Days)", "❌ Delete Student")
        AlertDialog.Builder(this)
            .setTitle("${student.name} Options")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> confirmRenew(student)
                    1 -> confirmDelete(student)
                }
            }
            .show()
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
