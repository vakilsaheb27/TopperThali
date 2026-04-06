package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val mobile: String,

    val planType: String, // LUNCH / DINNER / BOTH

    val startDate: String,

    val endDate: String,

    val remainingDays: Int,

    val status: String, // ACTIVE / EXPIRED

    val qrCode: String
)
