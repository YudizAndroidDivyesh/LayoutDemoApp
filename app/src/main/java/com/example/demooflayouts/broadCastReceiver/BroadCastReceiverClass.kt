package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class BroadCastReceiverClass(tv: TextView) : BroadcastReceiver() {
    private val tv : TextView = tv
    override fun onReceive(context: Context?, intent: Intent?) {
        val percentage = intent?.getIntExtra("level",0)
        if (percentage != 0) {
            tv.text = "Your Battery Percentage Is $percentage%"
        }
    }
}