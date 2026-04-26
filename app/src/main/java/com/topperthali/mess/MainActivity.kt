package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.topperthali.mess.ui.students.StudentListActivity

class MainActivity : AppCompatActivity() {

    // 1. Register the barcode scanner launcher
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            // 2. We successfully scanned a QR Code!
            val scannedQrData = result.contents
            
            // For now, let's just show the scanned ID on the screen
            Toast.makeText(this, "Success! Scanned ID: $scannedQrData", Toast.LENGTH_LONG).show()
            
            // Next step will be: 
            // 1. Search database for this qrData
            // 2. Check if student has remaining credits
            // 3. Mark attendance & deduct 1 credit
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardScanQr = findViewById<MaterialCardView>(R.id.cardScanQr)
        val cardManageStudents = findViewById<MaterialCardView>(R.id.cardManageStudents)

        cardScanQr.setOnClickListener {
            // 3. Configure and launch the camera scanner
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Only look for QR codes
            options.setPrompt("Scan Student's Topper Thali QR Code")
            options.setCameraId(0) // Use back camera
            options.setBeepEnabled(true) // Beep on success
            options.setOrientationLocked(false) // Allow portrait/landscape
            
            barcodeLauncher.launch(options)
        }

        cardManageStudents.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }
}
