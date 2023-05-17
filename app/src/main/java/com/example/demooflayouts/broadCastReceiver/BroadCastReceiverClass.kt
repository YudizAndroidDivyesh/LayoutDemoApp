package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadCastReceiverClass : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Action",intent?.action.toString())
        when(intent?.action){
            "Start" -> Log.d("Start","Register")
            "Stop" -> Log.d("Stop","UnRegister")
        }
    }
}