package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class BroadCastReceiverClass : BroadcastReceiver() {

    val ACTION_KEY : String = "MY_ACTION"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("Action", intent.toString())
        Log.d("Context", context.toString())

//        val percentageData = intent.getIntExtra("level", 0)
//        Log.d("percentageData",percentageData.toString())
//        actionOnBroadCast.register(percentageData)
//        actionOnBroadCast.actionOnBroadCast(percentageData)
//        Log.d("Status", intent.action.toString())

        // actionOnBroadCast.liveBatteryData.text = "Your battery percentage is "+percentageData.toString()+"%"
        try {

            when (intent.action) {
                "Start" -> {

//                    val intentFilter = IntentFilter("com.example.demooflayouts.broadCastReceiver.broadCastReceiver.BroadCastPercentageActivity")
//
//                    var batteryStatus: Intent? = null
//                  //  register(percentageData)
//                    batteryStatus = context.registerReceiver(null, intentFilter)
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
//                    context.unregisterReceiver(this)
                     Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

}