package com.example.demooflayouts.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import android.widget.Toast

class BroadCastReceiverClass : BroadcastReceiver(){



    override fun onReceive(context: Context?, intent: Intent) {
        val actionOnBroadCast = ActionOnBroadCastActivity()
        Log.d("Action",intent.action.toString())
        val percentageData = intent.getIntExtra("level",0)

       // actionOnBroadCast.liveBatteryData.text = "Your battery percentage is "+percentageData.toString()+"%"
      try{
        when(intent.action){
            "Start" -> {
               actionOnBroadCast.register(percentageData)
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
            }
            "Stop" -> {
                actionOnBroadCast.unregister()
                Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
            }
        }}catch (e : Exception){
          Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
        }

    }

}