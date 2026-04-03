package com.topperthali.mess.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.topperthali.mess.data.dao.MessDao
import com.topperthali.mess.data.entities.AttendanceEntity
import com.topperthali.mess.data.entities.PaymentEntity
import com.topperthali.mess.data.entities.StudentEntity

@Database(
    entities = [StudentEntity::class, AttendanceEntity::class, PaymentEntity::class],
    version = 1,
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
                    "topper_thali_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
