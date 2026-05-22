package com.topperthali.mess.ui.students

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
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

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilMobile = findViewById<TextInputLayout>(R.id.tilMobile)
        val etStudentName = findViewById<EditText>(R.id.etName)
        val etStudentPhone = findViewById<EditText>(R.id.etMobile)
        val btnSaveStudent = findViewById<Button>(R.id.btnAdd)

        etStudentName.requestFocus()

        etStudentName.doOnTextChanged { _, _, _, _ -> tilName.error = null }
        etStudentPhone.doOnTextChanged { _, _, _, _ -> tilMobile.error = null }

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            var hasError = false
            if (name.isEmpty()) {
                tilName.error = "Name is required"
                hasError = true
            }
            if (phone.isEmpty()) {
                tilMobile.error = "Mobile number is required"
                hasError = true
            }

            if (hasError) return@setOnClickListener

            btnSaveStudent.isEnabled = false
            btnSaveStudent.text = "Saving..."

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
