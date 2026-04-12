package com.topperthali.mess.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.topperthali.mess.data.dao.MessDao
import com.topperthali.mess.data.entities.*

@Database(
    entities = [
        StudentEntity::class,
        SubscriptionEntity::class,
        AttendanceEntity::class,
        PaymentEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MessDatabase : RoomDatabase() {

    abstract fun messDao(): MessDao

    companion object {
        @Volatile
        private var INSTANCE: MessDatabase? = null

        fun getDatabase(context: Context): MessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessDatabase::class.java,
                    "mess_database"
                )
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
