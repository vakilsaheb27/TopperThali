package com.topperthali.mess.data.dao

import androidx.room.*
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.data.entities.AttendanceEntity

@Dao
interface MessDao {

    // ---------------- STUDENT ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students ORDER BY id DESC")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE qrData = :qr")
    suspend fun getStudentByQr(qr: String): StudentEntity?

    // ---------------- ATTENDANCE ---------------- //

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAttendance(attendance: AttendanceEntity)
}
