package com.example.demooflayouts.koinDI

import androidx.lifecycle.ViewModel

class UserKoinViewModel(private val repository : UserKoinRepo) : ViewModel() {
    fun findUserInfo(name : String) : String{
        val foundUser = repository.findUser(name)
        return foundUser.toString()
    }
}