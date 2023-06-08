package com.example.demooflayouts

import android.app.Application
import com.example.demooflayouts.koinDI.appModule
import com.example.demooflayouts.koinDI.data
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinModule :Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@KoinModule)
            modules(appModule, data)
        }
    }
}