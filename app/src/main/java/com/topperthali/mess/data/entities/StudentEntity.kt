package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val mobile: String,

    val planType: String,

    val startDate: String,

    val endDate: String,

    val remainingDays: Int,

    val status: String,

    val qrCode: String
) {
    // ✅ Secondary constructor for old code compatibility
    constructor(name: String, mobile: String) : this(
        id = 0,
        name = name,
        mobile = mobile,
        planType = "LUNCH",
        startDate = "",
        endDate = "",
        remainingDays = 0,
        status = "ACTIVE",
        qrCode = ""
    )
}
