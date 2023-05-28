package com.example.demooflayouts.roomDBTask

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDetails::class, TaskDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDetailDao(): UserDetailDao
    abstract fun taskDetailDao(): TaskDetailsDao

    companion object {
        @Volatile
        private var INSTANCES: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCES == null) {
                synchronized(this) {
                    INSTANCES = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "userDetailsDB"
                    ).build()
                }
            }
            return INSTANCES!!
        }
    }

}

