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

        findViewById<MaterialCardView>(R.id.cardScanQr).setOnClickListener {
            Toast.makeText(this, "QR Scanner coming soon!", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialCardView>(R.id.cardManageStudents).setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }
}
