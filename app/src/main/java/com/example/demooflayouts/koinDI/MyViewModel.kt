package com.example.demooflayouts.koinDI

import androidx.lifecycle.ViewModel

// this is called constructor injection
class MyViewModel(private var repository : UserKoinServices) : ViewModel(){

    fun doNetworkCall() : String{
       return "Call From view Model"
    }
}