package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments_table")
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val paymentId: Int = 0,
    val studentId: Int,
    val amountPaid: Double,
    val receiptNumber: String,
    val paymentDate: Long = System.currentTimeMillis()
)
