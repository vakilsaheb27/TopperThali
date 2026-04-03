package com.topperthali.mess.ui.students

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
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

        val etStudentName = findViewById<TextInputEditText>(R.id.etStudentName)
        val etStudentPhone = findViewById<TextInputEditText>(R.id.etStudentPhone)
        val btnSaveStudent = findViewById<MaterialButton>(R.id.btnSaveStudent)

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uniqueQrData = UUID.randomUUID().toString()
            val newStudent = StudentEntity(name = name, phone = phone, qrData = uniqueQrData, creditsRemaining = 30)

            lifecycleScope.launch(Dispatchers.IO) {
                val db = MessDatabase.getDatabase(this@AddStudentActivity)
                db.messDao().insertStudent(newStudent)

                withContext(Dispatchers.Main) {
                    showQrDialog(name, uniqueQrData)
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
