package com.example.demooflayouts.liveDataViewModel.viewModelFile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demooflayouts.liveDataViewModel.data.Articles
import com.example.demooflayouts.liveDataViewModel.data.UserInfo
import com.example.demooflayouts.liveDataViewModel.repository.MainRepository
import kotlinx.coroutines.launch

class UserModel(private val repository: MainRepository= MainRepository()) : ViewModel() {

    private val _userDetail = MutableLiveData<UserInfo>()
    val detail = _userDetail


    fun saveDetails(data : UserInfo){
        _userDetail.value = data
    }

    private val _topicData = MutableLiveData<List<Articles>>()
    val topicList = _topicData

    fun data(topicName : String){
        viewModelScope.launch {
             _topicData.value = repository.fetchData(topicName)
        }
    }
}