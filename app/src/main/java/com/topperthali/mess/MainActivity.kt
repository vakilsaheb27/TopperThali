package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.topperthali.mess.ui.students.AddStudentActivity

class MainActivity : AppCompatActivity() {

    // 1. Initialize the QR Scanner Launcher
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            val scannedQrData = result.contents
            // TODO: In the next phase, we will query the Room DB with this data
            // to mark attendance and deduct 1 meal credit!
            Toast.makeText(this, "Success! Scanned: $scannedQrData", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find our UI Cards
        val cardScanQr = findViewById<MaterialCardView>(R.id.cardScanQr)
        val cardManageStudents = findViewById<MaterialCardView>(R.id.cardManageStudents)

        // Set Click Listeners
        cardScanQr.setOnClickListener {
            launchScanner()
        }

        cardManageStudents.setOnClickListener {
            // Launch the Add Student Activity
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    // 2. Configure and launch the scanner
    private fun launchScanner() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan Student QR Code\nVolume keys to toggle flash")
        options.setCameraId(0) // 0 = Rear camera
        options.setBeepEnabled(true) // Beep on successful scan
        options.setOrientationLocked(false) // Allow screen rotation
        
        // Launch the camera
        barcodeLauncher.launch(options)
    }
}
