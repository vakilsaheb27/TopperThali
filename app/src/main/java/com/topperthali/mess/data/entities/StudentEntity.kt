package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_table")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Int = 0,
    val name: String,
    val phone: String,
    val qrData: String, // Unique ID for QR code
    val creditsRemaining: Int = 30, // 30-day cycle
    val isOnHoliday: Boolean = false,
    val startDate: Long = System.currentTimeMillis()
)
