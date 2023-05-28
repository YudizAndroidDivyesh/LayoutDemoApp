package com.example.demooflayouts.roomDBTask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class UserDetails(
    @PrimaryKey(autoGenerate = true) var userId: Long = 0,
    @ColumnInfo(name = "user_name") val user_name: String?,
    @ColumnInfo(name = "user_email") val user_email: String?,
    @ColumnInfo(name = "user_phone") val user_phone: String?,
    @ColumnInfo(name = "user_password") val user_password: String?
)
