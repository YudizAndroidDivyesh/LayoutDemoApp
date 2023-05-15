package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.demooflayouts.CHANNEL_ID
import com.example.demooflayouts.R

class BroadCastPercentageActivity : AppCompatActivity() {
    lateinit var batteryPercentage : BroadCastReceiverClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_percentage)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "First Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "This description of Notification"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val notificationLayout = RemoteViews(packageName, R.layout.custme_notification_layout)

            val builder = NotificationCompat.Builder(this, "BroadCast")
            builder.apply {
                setSmallIcon(R.drawable.baseline_settings_24)
                setContentTitle("Battery Percentage")
                setStyle(NotificationCompat.DecoratedCustomViewStyle())
                setCustomContentView(notificationLayout)
            }
            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        }
       val tv = findViewById<TextView>(R.id.statusOfBattery)
       batteryPercentage = BroadCastReceiverClass(tv)
       registerReceiver(batteryPercentage, IntentFilter(Intent.ACTION_BATTERY_CHANGED))


    }
}