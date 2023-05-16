package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demooflayouts.CHANNEL_ID
import com.example.demooflayouts.R

class BroadCastPercentageActivity : AppCompatActivity() {

    lateinit var  tv : TextView
    private lateinit var builder : NotificationCompat.Builder
    private var data = "Hello"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_percentage)
        tv = findViewById(R.id.statusOfBattery)

        registerReceiver(batteryData, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        findViewById<Button>(R.id.batterySettings).setOnClickListener {
            startActivity(Intent(this,ActionOnBroadCastActivity::class.java))
        }
    }

    private fun batteryNotification() {
        createNotificationChannel()
        builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.apply {
            setSmallIcon(R.drawable.baseline_settings_24)
            setContentTitle("Activity First")
            setContentText(data)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        with(NotificationManagerCompat.from(this)){
            notify(2,builder.build())
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"First Channel",NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "This description of Notification"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
    private val batteryData : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val percentageData = intent?.getIntExtra("level",0)
            tv.text = "Your battery percentage is "+percentageData.toString()+"%"
            data = tv.text.toString()
            batteryNotification()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryData)
    }
}