package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val studentId: Int = 0,

    val date: String = "",

    val mealType: String = "LUNCH",

    val status: String = "PRESENT"
)
