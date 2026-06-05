package com.topperthali.mess.ui.students

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.topperthali.mess.R
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.utils.QrGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etStudentName = findViewById<EditText>(R.id.etName)
        val etStudentPhone = findViewById<EditText>(R.id.etMobile)
        val btnSaveStudent = findViewById<Button>(R.id.btnAdd)

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString().trim()
            val phone = etStudentPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
        val btnShare = dialogView.findViewById<Button>(R.id.btnShareDialog)
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

        btnShare.setOnClickListener {
            if (qrBitmap != null) {
                shareQrCode(name, qrBitmap)
            } else {
                Toast.makeText(this, "QR Code not available to share", Toast.LENGTH_SHORT).show()
            }
        }

        btnDone.setOnClickListener {
            dialog.dismiss()
            finish() // Go back to dashboard
        }
        dialog.show()
    }

    private fun shareQrCode(name: String, bitmap: Bitmap) {
        try {
            val cachePath = File(cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/qr_code.png")
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val imagePath = File(cacheDir, "images")
            val newFile = File(imagePath, "qr_code.png")
            val contentUri: Uri = FileProvider.getUriForFile(this, "com.topperthali.mess.fileprovider", newFile)

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(contentUri, contentResolver.getType(contentUri))
                putExtra(Intent.EXTRA_STREAM, contentUri)
                putExtra(Intent.EXTRA_TEXT, "QR Code for $name")
                type = "image/png"
            }
            startActivity(Intent.createChooser(shareIntent, "Share QR Code"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error sharing QR Code", Toast.LENGTH_SHORT).show()
        }
    }
}
