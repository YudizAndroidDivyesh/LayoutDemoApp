package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demooflayouts.R
import java.util.Objects

class ActionOnBroadCastActivity : AppCompatActivity() {

    lateinit var statusTv : TextView
    lateinit var liveBatteryData : TextView
   private var data = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_on_broad_cast)

        statusTv = findViewById(R.id.status_tv)
        liveBatteryData = findViewById(R.id.liveStatusOfBattery_tv)

        onStart()
    }

    private fun actionOnBroadCast() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("BatteryAction","BatteryChannel",NotificationManager.IMPORTANCE_HIGH)
            channel.description = "This Is Battery BroadCast"

            val intent = Intent(this,ActionOnBroadCastActivity::class.java)
           // val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

            val layoutRemote = RemoteViews(packageName,R.layout.custom_expanded_notifiction)
            layoutRemote.setOnClickPendingIntent(R.id.broadCast_start,
                PendingIntent.getBroadcast(this,0, Intent(this,BroadCastReceiverClass::class.java).setAction("Start"),PendingIntent.FLAG_MUTABLE))
            layoutRemote.setOnClickPendingIntent(R.id.broadCast_stop,
                PendingIntent.getBroadcast(this,0, Intent(this,BroadCastReceiverClass::class.java).setAction("Stop"),PendingIntent.FLAG_MUTABLE))

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(this,"BatteryAction")
            builder.apply {
                setSmallIcon(R.drawable.baseline_battery_charging_full_24)
                setContentTitle("Activity Second")
                setPriority(NotificationCompat.PRIORITY_HIGH)
              //  setContentIntent(pendingIntent)
                setAutoCancel(true)
                layoutRemote.setTextViewText(R.id.statusCheck_tv,getString(R.string.batteryPercentage)+" "+data)
                setCustomBigContentView(layoutRemote)
            }
            manager.notify(0,builder.build())
        }
    }

    private val batteryBroadCast = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                Log.d("BroadCast ","${intent.action}")
            }
            val percentageData = intent?.getIntExtra("level",0)
            liveBatteryData.text = percentageData.toString()
            data = liveBatteryData.text.toString()
            actionOnBroadCast()
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(batteryBroadCast, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryBroadCast)
    }
}