package com.topperthali.mess.ui.students

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        etStudentName.doAfterTextChanged { tilName.error = null }
        etStudentPhone.doAfterTextChanged { tilMobile.error = null }

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            var isValid = true
            if (name.isEmpty()) {
                tilName.error = "Name is required"
                isValid = false
            }
            if (phone.length != 10) {
                tilMobile.error = "Enter a valid 10-digit number"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val qrCode = UUID.randomUUID().toString()
            val newStudent = StudentEntity(name = name, mobile = phone, qrCode = qrCode, creditsRemaining = 30)

            // Launch coroutine on IO thread to prevent UI freezing
            lifecycleScope.launch(Dispatchers.IO) {
                val db = MessDatabase.getDatabase(this@AddStudentActivity)
                db.messDao().insertStudent(newStudent)

                // Generate QR code in the background
                val qrBitmap = QrGenerator.generateQrCode(qrCode)

                // Switch back to Main thread to update the UI
                withContext(Dispatchers.Main) {
                    showQrDialog(name, qrBitmap)
                }
            }
        }
    }

    private fun showQrDialog(name: String, qrBitmap: Bitmap?) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_qr_code, null)
        val tvName = dialogView.findViewById<TextView>(R.id.tvStudentNameDialog)
        val ivQr = dialogView.findViewById<ImageView>(R.id.ivQrCodeDialog)
        val btnDone = dialogView.findViewById<Button>(R.id.btnDoneDialog)

        tvName.text = name
        
        if (qrBitmap != null) {
            ivQr.setImageBitmap(qrBitmap)
        } else {
            Toast.makeText(this, "Failed to generate QR Code", Toast.LENGTH_SHORT).show()
        }

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
