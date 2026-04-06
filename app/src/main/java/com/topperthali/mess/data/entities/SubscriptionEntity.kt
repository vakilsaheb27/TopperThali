package com.topperthali.mess.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val studentId: Int,

    val planType: String, // LUNCH / DINNER / BOTH

    val startDate: String,

    val endDate: String,

    val remainingDays: Int,

    val status: String // ACTIVE / EXPIRED
)
