package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.topperthali.mess.ui.students.AddStudentNewActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simple UI (no XML dependency)
        val button = Button(this)
        button.text = "Add Student"

        button.setOnClickListener {
            startActivity(Intent(this, AddStudentNewActivity::class.java))
        }

        setContentView(button)
    }
}
