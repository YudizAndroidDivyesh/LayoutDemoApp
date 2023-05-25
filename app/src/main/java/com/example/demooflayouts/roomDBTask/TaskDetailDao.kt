package com.example.demooflayouts.roomDBTask

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDetailDao {

    @Insert
    fun insertTask(taskDetail : TaskDetail)

    @Query("select * from userDetails where user_email=:email and user_password=:password")
    fun getOneRecord(email : String,password : String) : List<TaskDetail>

}