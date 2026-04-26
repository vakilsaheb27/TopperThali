package com.topperthali.mess.ui.students

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentListActivity : AppCompatActivity() {

    private lateinit var rvStudents: RecyclerView
    private lateinit var llEmptyState: LinearLayout
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        rvStudents = findViewById(R.id.rvStudents)
        rvStudents.layoutManager = LinearLayoutManager(this)
        llEmptyState = findViewById(R.id.llEmptyState)

        val fabAddStudent = findViewById<FloatingActionButton>(R.id.fabAddStudent)
        fabAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // We load the list inside onResume so it updates automatically 
        // when we return from adding a new student.
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
                    adapter = StudentAdapter(studentList)
                    rvStudents.adapter = adapter
                }
            }
        }
    }
}
