package com.example.demooflayouts.roomDBTask

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDetailsDao {
    @Insert
    fun insertTask(taskDetails: TaskDetails)

    @Query("select * from taskDetail where email=:userEmail")
    fun getAllTaskRecord(userEmail: String): LiveData<List<TaskDetails>>

    @Query("select * from taskDetail where taskId=:id")
    fun getOneRecord(id: Int): List<TaskDetails>

    @Query("delete from taskDetail where taskId=:id")
    fun deleteTask(id: Int)

    @Query("update taskDetail set task_title=:title ,task_desc=:desc where taskId=:id")
    fun updateTask(title: String, desc: String, id: Int)

}