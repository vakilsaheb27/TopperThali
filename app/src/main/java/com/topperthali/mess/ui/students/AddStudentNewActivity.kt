package com.topperthali.mess.ui.students

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var nameInput: EditText
    private lateinit var mobileInput: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        tilName = findViewById(R.id.tilName)
        tilMobile = findViewById(R.id.tilMobile)
        nameInput = findViewById(R.id.etName)
        mobileInput = findViewById(R.id.etMobile)
        addButton = findViewById(R.id.btnAdd)

        setupTextWatchers()

        addButton.setOnClickListener {
            saveStudent()
        }
    }

    private fun setupTextWatchers() {
        nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tilName.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        mobileInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tilMobile.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
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
