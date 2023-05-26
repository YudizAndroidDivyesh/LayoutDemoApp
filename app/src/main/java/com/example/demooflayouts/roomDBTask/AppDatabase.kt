package com.example.demooflayouts.roomDBTask

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDetails::class,TaskDetails::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDetailDao() : UserDetailDao

    abstract fun taskDetailDao() : TaskDetailsDao

}

