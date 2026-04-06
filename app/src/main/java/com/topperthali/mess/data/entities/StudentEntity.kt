package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    // OLD FIELD (used in UI)
    val phone: String,

    // NEW FIELD (same value)
    val mobile: String = phone,

    // OLD FIELD
    val qrData: String = "",

    // OLD FIELD
    val creditsRemaining: Int = 0,

    // NEW FIELDS (for future)
    val planType: String = "LUNCH",
    val startDate: String = "",
    val endDate: String = "",
    val remainingDays: Int = 0,
    val status: String = "ACTIVE",
    val qrCode: String = ""
)
