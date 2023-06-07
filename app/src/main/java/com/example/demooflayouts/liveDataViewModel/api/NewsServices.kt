package com.example.demooflayouts.liveDataViewModel.api

import com.example.demooflayouts.liveDataViewModel.data.NewsDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsServices {
    @GET("everything?apiKey=9052eadf429344b7926a2d7a0c8c25bc")
    suspend fun getQueryNews(@Query("q")topicName : String) : Response<NewsDetails>
}