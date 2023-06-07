package com.example.demooflayouts.liveDataViewModel.repository

import androidx.lifecycle.MutableLiveData
import com.example.demooflayouts.liveDataViewModel.data.Articles
import com.example.demooflayouts.liveDataViewModel.util.ApiBuilder
import kotlinx.coroutines.*

class MainRepository() {
     val topicData = MutableLiveData<List<Articles>>()
     val loadError = MutableLiveData<String>()
     var loading = MutableLiveData<Boolean>()

     suspend fun fetchData(topicName : String) : List<Articles>?{
          loading.value = true

          val response = ApiBuilder.createConnection()?.getQueryNews(topicName)
          return if(response?.isSuccessful == true){
               response.body()?.articles
          }else{
               onError("Error : ${response?.message()}")
               null
          }
     }

     private fun onError(resMsg: String) {
          loadError.value = resMsg
          loading.value = false

     }
}