package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddStudentNewActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var tilMobile: TextInputLayout
    private lateinit var nameInput: TextInputEditText
    private lateinit var mobileInput: TextInputEditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        tilName = findViewById(R.id.tilName)
        tilMobile = findViewById(R.id.tilMobile)
        nameInput = findViewById(R.id.etName)
        mobileInput = findViewById(R.id.etMobile)
        addButton = findViewById(R.id.btnAdd)

        nameInput.doOnTextChanged { _, _, _, _ -> tilName.error = null }
        mobileInput.doOnTextChanged { _, _, _, _ -> tilMobile.error = null }

        addButton.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = nameInput.text.toString().trim()
        val mobile = mobileInput.text.toString().trim()

        var isValid = true
        if (name.isEmpty()) {
            tilName.error = "Name is required"
            isValid = false
        }
        if (mobile.isEmpty()) {
            tilMobile.error = "Mobile number is required"
            isValid = false
        }

        if (!isValid) return

        val qrCode = UUID.randomUUID().toString()

        val student = StudentEntity(
            name = name,
            mobile = mobile,
            qrCode = qrCode,
            creditsRemaining = 30
        )

        val db = MessDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            db.messDao().insertStudent(student)

            runOnUiThread {
                Toast.makeText(this@AddStudentNewActivity, "Student Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
