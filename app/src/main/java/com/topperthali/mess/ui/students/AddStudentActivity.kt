package com.topperthali.mess.ui.students

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
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

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilMobile = findViewById<TextInputLayout>(R.id.tilMobile)
        val etStudentName = findViewById<TextInputEditText>(R.id.etName)
        val etStudentPhone = findViewById<TextInputEditText>(R.id.etMobile)
        val btnSaveStudent = findViewById<Button>(R.id.btnAdd)

        topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        etStudentName.doAfterTextChanged { tilName.error = null }
        etStudentPhone.doAfterTextChanged { tilMobile.error = null }

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            var hasError = false
            if (name.isEmpty()) {
                tilName.error = getString(R.string.error_name_required)
                hasError = true
            }
            if (phone.isEmpty()) {
                tilMobile.error = getString(R.string.error_mobile_required)
                hasError = true
            } else if (phone.length != 10) {
                tilMobile.error = getString(R.string.error_invalid_mobile)
                hasError = true
            }

            if (hasError) return@setOnClickListener

            val qrCode = UUID.randomUUID().toString()
            val newStudent = StudentEntity(name = name, mobile = phone, qrCode = qrCode, creditsRemaining = 30)

            lifecycleScope.launch(Dispatchers.IO) {
                val db = MessDatabase.getDatabase(this@AddStudentActivity)
                db.messDao().insertStudent(newStudent)

                val qrBitmap = QrGenerator.generateQrCode(qrCode)

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
            finish()
        }
        dialog.show()
    }
}
