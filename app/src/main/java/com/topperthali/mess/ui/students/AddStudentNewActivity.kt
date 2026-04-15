package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddStudentNewActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var mobileInput: EditText
    private lateinit var addButton: Button
    private lateinit var tilName: TextInputLayout
    private lateinit var tilMobile: TextInputLayout

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

        var hasError = false
        if (name.isEmpty()) {
            tilName.error = "Name is required"
            hasError = true
        }
        if (mobile.isEmpty()) {
            tilMobile.error = "Mobile number is required"
            hasError = true
        }

        if (hasError) return

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
