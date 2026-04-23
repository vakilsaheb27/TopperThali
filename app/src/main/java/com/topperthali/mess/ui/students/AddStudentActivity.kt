package com.topperthali.mess.ui.students

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.utils.QrGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilMobile = findViewById<TextInputLayout>(R.id.tilMobile)
        val etStudentName = findViewById<EditText>(R.id.etName)
        val etStudentPhone = findViewById<EditText>(R.id.etMobile)
        val btnSaveStudent = findViewById<Button>(R.id.btnAdd)

        // Clear errors when typing
        etStudentName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tilName.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        etStudentPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tilMobile.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            var isValid = true
            if (name.isEmpty()) {
                tilName.error = "Name is required"
                isValid = false
            }
            if (phone.isEmpty()) {
                tilMobile.error = "Mobile number is required"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val qrCode = UUID.randomUUID().toString()
            val newStudent = StudentEntity(name = name, mobile = phone, qrCode = qrCode, creditsRemaining = 30)

            lifecycleScope.launch(Dispatchers.IO) {
                val db = MessDatabase.getDatabase(this@AddStudentActivity)
                db.messDao().insertStudent(newStudent)

                withContext(Dispatchers.Main) {
                    showQrDialog(name, qrCode)
                }
            }
        }
    }

    private fun showQrDialog(name: String, qrData: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_qr_code, null)
        val tvName = dialogView.findViewById<TextView>(R.id.tvStudentNameDialog)
        val ivQr = dialogView.findViewById<ImageView>(R.id.ivQrCodeDialog)
        val btnDone = dialogView.findViewById<Button>(R.id.btnDoneDialog)

        tvName.text = name
        ivQr.setImageBitmap(QrGenerator.generateQrCode(qrData))

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnDone.setOnClickListener {
            dialog.dismiss()
            finish() // Go back to dashboard
        }
        dialog.show()
    }
}
