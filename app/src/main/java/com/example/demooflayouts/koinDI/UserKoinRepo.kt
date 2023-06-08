package com.example.demooflayouts.koinDI

interface UserKoinRepo {
    fun findUser(name : String) :String
}

class UserRepoImpl : UserKoinRepo{

    override fun findUser(name: String): String {
        return name
    }
}