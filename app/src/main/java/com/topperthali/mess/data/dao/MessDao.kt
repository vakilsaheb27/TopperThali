package com.topperthali.mess.data.dao

import androidx.room.*
import com.topperthali.mess.data.entities.StudentEntity
import com.topperthali.mess.data.entities.AttendanceEntity

@Dao
interface MessDao {

    // ---------------- STUDENT OPERATIONS ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query("SELECT * FROM students ORDER BY id DESC")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE id = :studentId")
    suspend fun getStudentById(studentId: Int): StudentEntity?

    @Query("SELECT * FROM students WHERE status = 'ACTIVE'")
    suspend fun getActiveStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE status = 'EXPIRED'")
    suspend fun getExpiredStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE name LIKE '%' || :query || '%' OR mobile LIKE '%' || :query || '%'")
    suspend fun searchStudents(query: String): List<StudentEntity>


    // ---------------- ATTENDANCE OPERATIONS ---------------- //

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Query("SELECT * FROM attendance WHERE studentId = :studentId")
    suspend fun getAttendanceByStudent(studentId: Int): List<AttendanceEntity>

    @Query("""
        SELECT COUNT(*) FROM attendance 
        WHERE date = :date AND mealType = 'LUNCH' AND status = 'PRESENT'
    """)
    suspend fun getTodayLunchCount(date: String): Int

    @Query("""
        SELECT COUNT(*) FROM attendance 
        WHERE date = :date AND mealType = 'DINNER' AND status = 'PRESENT'
    """)
    suspend fun getTodayDinnerCount(date: String): Int
}
