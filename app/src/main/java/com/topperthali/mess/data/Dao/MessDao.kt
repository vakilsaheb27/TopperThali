package com.topperthali.mess.data.dao

import androidx.room.*
import com.topperthali.mess.data.entities.*

@Dao
interface MessDao {

    // ---------------- STUDENT ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<StudentEntity>


    // ---------------- SUBSCRIPTION ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(subscription: SubscriptionEntity)

    @Query("SELECT * FROM subscriptions WHERE studentId = :studentId")
    suspend fun getSubscriptionByStudent(studentId: Int): SubscriptionEntity?


    // ---------------- ATTENDANCE ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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


    // ---------------- PAYMENTS ---------------- //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity)

    @Query("SELECT * FROM payments WHERE studentId = :studentId")
    suspend fun getPaymentsByStudent(studentId: Int): List<PaymentEntity>
}
