package com.topperthali.mess.data.dao

import androidx.room.*
import com.topperthali.mess.data.entities.AttendanceEntity
import com.topperthali.mess.data.entities.PaymentEntity
import com.topperthali.mess.data.entities.StudentEntity

@Dao
interface MessDao {

    // --- Student Queries ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students_table ORDER BY name ASC")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students_table WHERE qrData = :qrCode LIMIT 1")
    suspend fun getStudentByQr(qrCode: String): StudentEntity?

    @Update
    suspend fun updateStudent(student: StudentEntity)

    // --- Attendance Queries ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Query("SELECT * FROM attendance_table WHERE studentId = :studentId ORDER BY scanTimestamp DESC")
    suspend fun getAttendanceForStudent(studentId: Int): List<AttendanceEntity>

    // --- Payment Queries ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity)
}
