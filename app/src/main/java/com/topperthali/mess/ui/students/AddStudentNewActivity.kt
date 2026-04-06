package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        nameInput = findViewById(R.id.etName)
        mobileInput = findViewById(R.id.etMobile)
        addButton = findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = nameInput.text.toString().trim()
        val mobile = mobileInput.text.toString().trim()

        if (name.isEmpty() || mobile.isEmpty()) {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        }

        val qrCode = UUID.randomUUID().toString()

        val student = StudentEntity(
            name = name,
            mobile = mobile,
            qrCode = qrCode
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
