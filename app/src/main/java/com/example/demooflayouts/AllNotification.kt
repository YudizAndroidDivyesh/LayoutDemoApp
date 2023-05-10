package com.example.demooflayouts

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class AllNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notification)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        findViewById<Button>(R.id.basicNotify).setOnClickListener {
            //Set the notification content
            // this notification works only on Below 8 version of android


            val builder = NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.baseline_flashlight_on_24)
                .setContentTitle("Basic Notification below 8.0")
                .setContentText("This is a Basic Notification With Icon")
//            you can enable an expandable notification by adding a style template with setStyle()
                .setStyle(NotificationCompat.BigTextStyle().bigText("This is a Basic Notification With BigTextStyle"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            manager.notify(1,builder.build())
        }

        findViewById<Button>(R.id.basicNotifyupper).setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val builder = NotificationCompat.Builder(this,"1")
                    .setSmallIcon(R.drawable.baseline_flashlight_on_24)
                    .setContentTitle("Basic Notification below 8.0")
                    .setContentText("This is a Basic Notification With Icon")
//            you can enable an expandable notification by adding a style template with setStyle()
                    .setStyle(NotificationCompat.BigTextStyle().bigText("This is a Basic Notification With BigTextStyle"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                manager.notify(1,builder.build())

            }
        }

        findViewById<Button>(R.id.notifyBtn).setOnClickListener {
            val selectPhoto = Intent(this,RuntimePermissionStartActivity::class.java).apply {
                action = Intent.ACTION_DEFAULT
                putExtra("Hello",1)
            }
            val photoPendingIntent = PendingIntent.getBroadcast(this,0,selectPhoto,PendingIntent.FLAG_MUTABLE)
            // Button on Notification For Action
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R){
                val builder = NotificationCompat.Builder(this,"1")
                    .setSmallIcon(R.drawable.baseline_settings_24)
                    .setContentTitle("Action With Button")
                    .setContentText("This is a Button For Action")
                    .setContentIntent(photoPendingIntent)
                    .addAction(R.drawable.baseline_adb_24,getString(R.string.app_name),photoPendingIntent)

                manager.notify(1,builder.build())

            }
        }
        findViewById<Button>(R.id.notifyTap).setOnClickListener {
            val selectPhoto = Intent(this,RuntimePermissionStartActivity::class.java).apply {
              flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val photoPendingIntent = PendingIntent.getBroadcast(this,0,selectPhoto,0)
            // Button on Notification For Action
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R){
                val builder = NotificationCompat.Builder(this,"1")
                    .setSmallIcon(R.drawable.baseline_settings_24)
                    .setContentTitle("Action With Button")
                    .setContentText("This is a Button For Action")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                manager.notify(1,builder.build())

            }
        }

    }
}