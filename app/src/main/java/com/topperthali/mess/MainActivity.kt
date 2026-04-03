package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.card.MaterialCardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.topperthali.mess.data.MessDatabase
import com.topperthali.mess.data.entities.AttendanceEntity
import com.topperthali.mess.ui.students.StudentListActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents != null) {
            processAttendance(result.contents)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialCardView>(R.id.cardScanQr).setOnClickListener {
            launchScanner()
        }

        findViewById<MaterialCardView>(R.id.cardManageStudents).setOnClickListener {
            // UPDATED: This now opens the Student List instead of the Add Student screen
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }

    private fun launchScanner() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan Student QR Code")
        options.setBeepEnabled(true)
        options.setOrientationLocked(false)
        barcodeLauncher.launch(options)
    }

    private fun processAttendance(qrData: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@MainActivity)
            val dao = db.messDao()
            val student = dao.getStudentByQr(qrData)

            withContext(Dispatchers.Main) {
                if (student == null) {
                    Toast.makeText(this@MainActivity, "❌ Invalid QR Code!", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                if (student.isOnHoliday) {
                    Toast.makeText(this@MainActivity, "⚠️ ${student.name} is on Holiday Pause!", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                if (student.creditsRemaining <= 0) {
                    Toast.makeText(this@MainActivity, "⛔ ${student.name}: Subscription Ended!", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                // Deduct 1 credit and record attendance
                val updatedStudent = student.copy(creditsRemaining = student.creditsRemaining - 1)
                val attendance = AttendanceEntity(studentId = student.studentId, mealType = "SCANNED")
                
                // Save updates in background
                launch(Dispatchers.IO) {
                    dao.updateStudent(updatedStudent)
                    dao.insertAttendance(attendance)
                }

                Toast.makeText(this@MainActivity, "✅ Success! ${student.name}: ${updatedStudent.creditsRemaining} days left.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
