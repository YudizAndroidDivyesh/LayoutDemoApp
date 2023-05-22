package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class BroadCastReceiverClass : BroadcastReceiver() {
     var batteryStatus: Intent? = null
      override fun onReceive(context: Context, intent: Intent) {
        Log.d("Action", intent.toString())

//        val percentageData = intent.getIntExtra("level", 0)
//        Log.d("percentageData",percentageData.toString())
//        actionOnBroadCast.register(percentageData)
//        actionOnBroadCast.actionOnBroadCast(percentageData)
//        Log.d("Status", intent.action.toString())

        // actionOnBroadCast.liveBatteryData.text = "Your battery percentage is "+percentageData.toString()+"%"
        try {
            val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

            when (intent.action) {
                "Start" -> {
                  //  register(percentageData)
                    batteryStatus = context.registerReceiver(null, intentFilter)
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
                     Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

}