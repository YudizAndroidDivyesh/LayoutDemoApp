package com.example.demooflayouts.broadCastReceiver

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context?= null

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}