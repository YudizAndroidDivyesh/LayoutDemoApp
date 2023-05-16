package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast

class BroadCastReceiverClass() : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            "Start" -> Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
            "Stop" -> Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
        }
    }
}