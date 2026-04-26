package com.topperthali.mess.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.topperthali.mess.data.dao.MessDao
import com.topperthali.mess.data.entities.AttendanceEntity
import com.topperthali.mess.data.entities.StudentEntity

@Database(
    entities = [
        StudentEntity::class,
        AttendanceEntity::class
    ],
    version = 3, // Incremented version for the clean slate
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
