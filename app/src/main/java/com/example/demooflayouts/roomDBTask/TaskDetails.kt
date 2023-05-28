package com.example.demooflayouts.roomDBTask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "taskDetail")
data class TaskDetails(
    @PrimaryKey(autoGenerate = true) val taskId: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_desc") val taskDesc: String,
    @ColumnInfo(name = "email") val userEmail: String
)