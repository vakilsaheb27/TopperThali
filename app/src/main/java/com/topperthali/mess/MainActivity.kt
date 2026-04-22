package com.topperthali.mess

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.topperthali.mess.ui.students.StudentListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardScanQr = findViewById<MaterialCardView>(R.id.cardScanQr)
        val cardManageStudents = findViewById<MaterialCardView>(R.id.cardManageStudents)

        cardScanQr.setOnClickListener {
            Toast.makeText(this, "Scan Attendance feature coming soon", Toast.LENGTH_SHORT).show()
        }

        cardManageStudents.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }
}
