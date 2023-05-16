package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class BroadCastReceiverClass(private var data: TextView) : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val percentageData = intent?.getIntExtra("level",0)
        data.text = percentageData.toString()
    }
}