package com.example.demooflayouts.koinDI

class UserKoinRepoIml(var api : UserApi) : UserKoinServices {
    override fun networkCall() {
        api.callApi()
    }

}