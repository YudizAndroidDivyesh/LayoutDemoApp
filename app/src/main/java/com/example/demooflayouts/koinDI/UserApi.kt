package com.example.demooflayouts.koinDI

import retrofit2.http.GET

interface UserApi {
    @GET("products/1")
    fun callApi()
}