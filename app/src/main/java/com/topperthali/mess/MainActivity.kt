package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvLunchCount: TextView
    private lateinit var tvDinnerCount: TextView

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            processAttendance(result.contents)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLunchCount = findViewById(R.id.tvLunchCount)
        tvDinnerCount = findViewById(R.id.tvDinnerCount)

        val cardScanQr = findViewById<MaterialCardView>(R.id.cardScanQr)
        val cardManageStudents = findViewById<MaterialCardView>(R.id.cardManageStudents)

        cardScanQr.setOnClickListener {
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            options.setPrompt("Scan Student's Topper Thali QR Code")
            options.setCameraId(0)
            options.setBeepEnabled(true)
            options.setOrientationLocked(false)
            barcodeLauncher.launch(options)
        }

        cardManageStudents.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadDailyStats()
    }

    private fun loadDailyStats() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@MainActivity)
            val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val lunchCount = db.messDao().getTodayLunchCount(todayDate)
            val dinnerCount = db.messDao().getTodayDinnerCount(todayDate)

            withContext(Dispatchers.Main) {
                tvLunchCount.text = lunchCount.toString()
                tvDinnerCount.text = dinnerCount.toString()
            }
        }
    }

    private fun processAttendance(qrData: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MessDatabase.getDatabase(this@MainActivity)
            val student = db.messDao().getStudentByQrCode(qrData)

            withContext(Dispatchers.Main) {
                if (student == null) {
                    Toast.makeText(this@MainActivity, "❌ Invalid QR Code", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                if (student.creditsRemaining <= 0) {
                    Toast.makeText(this@MainActivity, "⚠️ Access Denied: ${student.name} has 0 days left!", Toast.LENGTH_LONG).show()
                    return@withContext
                }

                val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                val mealType = if (currentHour < 16) "LUNCH" else "DINNER"
                val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                // 🚨 Double-Scan Protection Check
                lifecycleScope.launch(Dispatchers.IO) {
                    val alreadyScanned = db.messDao().checkAttendanceExists(student.id, todayDate, mealType) > 0
                    
                    withContext(Dispatchers.Main) {
                        if (alreadyScanned) {
                            Toast.makeText(this@MainActivity, "🛑 ${student.name} has already eaten $mealType today!", Toast.LENGTH_LONG).show()
                        } else {
                            // Safe to deduct credit
                            val updatedStudent = student.copy(creditsRemaining = student.creditsRemaining - 1)
                            val attendanceRecord = AttendanceEntity(
                                studentId = student.id,
                                date = todayDate,
                                mealType = mealType,
                                status = "PRESENT"
                            )

                            lifecycleScope.launch(Dispatchers.IO) {
                                db.messDao().updateStudent(updatedStudent)
                                db.messDao().insertAttendance(attendanceRecord)

                                withContext(Dispatchers.Main) {
                                    Toast.makeText(this@MainActivity, "✅ ${student.name} marked for $mealType.\nDays left: ${updatedStudent.creditsRemaining}", Toast.LENGTH_LONG).show()
                                    loadDailyStats()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
