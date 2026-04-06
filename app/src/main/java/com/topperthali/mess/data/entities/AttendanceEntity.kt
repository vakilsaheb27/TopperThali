package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val studentId: Int,

    val date: String,

    val mealType: String, // LUNCH / DINNER

    val status: String // PRESENT / ABSENT
)
