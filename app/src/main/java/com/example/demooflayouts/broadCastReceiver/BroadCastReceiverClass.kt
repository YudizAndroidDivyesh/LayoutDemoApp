package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import android.widget.Toast

class BroadCastReceiverClass : BroadcastReceiver {
    lateinit var actionOnBroadCast: ActionOnBroadCastActivity

    private constructor()
    constructor(action: ActionOnBroadCastActivity) {
        actionOnBroadCast = action
    }

    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("Action", context.toString())

//        val percentageData = intent.getIntExtra("level", 0)
//        Log.d("percentageData",percentageData.toString())
//        actionOnBroadCast.register(percentageData)
//        actionOnBroadCast.actionOnBroadCast(percentageData)
//        Log.d("Status", intent.action.toString())

        // actionOnBroadCast.liveBatteryData.text = "Your battery percentage is "+percentageData.toString()+"%"
        try {
            when (intent.action) {
                "Start" -> {
                  //  register(percentageData)
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
                   // unregister()
                    Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

}