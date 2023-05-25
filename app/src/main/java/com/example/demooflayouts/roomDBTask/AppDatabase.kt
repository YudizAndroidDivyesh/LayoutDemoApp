package com.example.demooflayouts.roomDBTask

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskDetail::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun taskDetailDao() : TaskDetailDao
}

