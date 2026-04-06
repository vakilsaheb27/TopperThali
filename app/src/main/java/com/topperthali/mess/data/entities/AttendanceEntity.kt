package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val studentId: Int,

    val date: String,

    val mealType: String,

    val status: String
) {
    // Optional helper constructor
    constructor(studentId: Int, date: String) : this(
        id = 0,
        studentId = studentId,
        date = date,
        mealType = "LUNCH",
        status = "PRESENT"
    )
}
