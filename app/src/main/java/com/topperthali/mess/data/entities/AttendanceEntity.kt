package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance_table")
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true)
    val attendanceId: Int = 0,
    val studentId: Int,
    val scanTimestamp: Long = System.currentTimeMillis(),
    val mealType: String // e.g., "LUNCH" or "DINNER"
)
