package com.example.demooflayouts.liveDataViewModel.util

import com.example.demooflayouts.liveDataViewModel.api.NewsServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiBuilder {
    companion object{
        private const val BASE_URL = "https://newsapi.org/v2/"

        private var newsServices : NewsServices? = null
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