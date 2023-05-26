package com.example.demooflayouts.roomDBTask

import androidx.room.*

@Dao
interface TaskDetailsDao {
  @Insert
  fun insertTask(taskDetails : TaskDetails)

  @Query("select * from taskDetail where email=:userEmail")
  fun getAllTaskRecord(userEmail : String) : List<TaskDetails>

  @Delete
  fun deleteTask(taskDetails : TaskDetails)

  @Update
  fun updateTask(taskDetails : TaskDetails)

}