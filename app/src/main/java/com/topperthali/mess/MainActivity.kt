package com.topperthali.mess

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find our UI Cards
        val cardScanQr = findViewById<MaterialCardView>(R.id.cardScanQr)
        val cardManageStudents = findViewById<MaterialCardView>(R.id.cardManageStudents)

        // Set Click Listeners
        cardScanQr.setOnClickListener {
            // TODO: We will launch the ZXing Scanner Activity here next!
            Toast.makeText(this, "Opening Scanner...", Toast.LENGTH_SHORT).show()
        }

        cardManageStudents.setOnClickListener {
            // TODO: We will launch the Student List Activity here later
            Toast.makeText(this, "Opening Students List...", Toast.LENGTH_SHORT).show()
        }
    }
}
