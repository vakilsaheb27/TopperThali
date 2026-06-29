package com.topperthali.mess.ui.students

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
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

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        rvStudents = findViewById(R.id.rvStudents)
        rvStudents.layoutManager = LinearLayoutManager(this)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter(newText ?: "")
                return true
            }
        })

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
                        showOptionsDialog(student)
                    }
                    rvStudents.adapter = adapter
                } else {
                    adapter?.updateData(studentList)
                    // Re-apply filter if there is active search text
                    val searchView = findViewById<SearchView>(R.id.searchView)
                    val currentQuery = searchView.query.toString()
                    if (currentQuery.isNotEmpty()) {
                        adapter?.filter(currentQuery)
                    }
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
