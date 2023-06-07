package com.example.demooflayouts.liveDataViewModel.util

import com.example.demooflayouts.liveDataViewModel.api.NewsServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiBuilder {
    companion object{
        val BASE_URL = "https://newsapi.org/v2/"

        var newsServices : NewsServices? = null
        fun createConnection(): NewsServices? {
            if(newsServices == null){
                val retroFit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                newsServices = retroFit.create(NewsServices::class.java)
            }
            return newsServices
        }
    }

}