package com.topperthali.mess.data.dao

import androidx.room.*
import com.topperthali.mess.data.entities.*

@Dao
interface MessDao {

    // ---------------- STUDENT ---------------- //
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity) // NEW: Ability to delete

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE qrCode = :qrCode LIMIT 1")
    suspend fun getStudentByQrCode(qrCode: String): StudentEntity?


    // ---------------- ATTENDANCE ---------------- //
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Query("SELECT COUNT(*) FROM attendance WHERE date = :date AND mealType = 'LUNCH' AND status = 'PRESENT'")
    suspend fun getTodayLunchCount(date: String): Int

    @Query("SELECT COUNT(*) FROM attendance WHERE date = :date AND mealType = 'DINNER' AND status = 'PRESENT'")
    suspend fun getTodayDinnerCount(date: String): Int

    // Prevents double-scanning for the same meal on the same day
    @Query("SELECT COUNT(*) FROM attendance WHERE studentId = :studentId AND date = :date AND mealType = :mealType")
    suspend fun checkAttendanceExists(studentId: Int, date: String, mealType: String): Int
}
