package com.example.demooflayouts.liveDataViewModel.api

import com.example.demooflayouts.liveDataViewModel.data.NewsDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsServices {
    @GET("everything?apiKey=a4ed7f051ca3457b89ebd06d2083b973")
    suspend fun getQueryNews(@Query("q")topicName : String) : Response<NewsDetails>
}