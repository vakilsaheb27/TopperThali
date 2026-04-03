package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Link our XML UI elements to Kotlin
        val etStudentName = findViewById<TextInputEditText>(R.id.etStudentName)
        val etStudentPhone = findViewById<TextInputEditText>(R.id.etStudentPhone)
        val btnSaveStudent = findViewById<MaterialButton>(R.id.btnSaveStudent)

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            // Basic validation
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Generate a unique, random string for this student's QR code
            val uniqueQrData = UUID.randomUUID().toString()

            // Create the new student object
            val newStudent = StudentEntity(
                name = name,
                phone = phone,
                qrData = uniqueQrData,
                creditsRemaining = 30 // Start with a fresh 30-day cycle
            )

            // Save to the Room Database in the background
            lifecycleScope.launch(Dispatchers.IO) {
                val db = MessDatabase.getDatabase(this@AddStudentActivity)
                db.messDao().insertStudent(newStudent)

                // Switch back to the Main thread to show a Toast and close the screen
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddStudentActivity, "Student Saved! QR Generated.", Toast.LENGTH_SHORT).show()
                    finish() // Closes this screen and goes back
                }
            }
        }
    }
}
