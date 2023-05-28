package com.example.demooflayouts.roomDBTask

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDetailDao {

    @Insert
    fun insertUserData(userDetails: UserDetails)

    @Query("select * from userDetails where user_email=:email and user_password=:password")
    fun getOneUserRecord(email: String, password: String): List<UserDetails>


}