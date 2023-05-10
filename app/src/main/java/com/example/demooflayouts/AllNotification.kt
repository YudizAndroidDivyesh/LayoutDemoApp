package com.example.demooflayouts

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

//Make a demo for below given functionalities,
//
//1) Normal notification (Collapsable/Expandable)
//2) Full screen notification
//3) Fully custom notification
//4) Channels

const val CHANNEL_ID = "channelId"
class AllNotification : AppCompatActivity() {

    private lateinit var builder : NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notification)

        findViewById<Button>(R.id.basicNotify).setOnClickListener {
            //Set the notification content
            // this notification works only on Below 8 version of android

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_flashlight_on_24)
                .setContentTitle("Basic Notification below 8.0")
                .setContentText("This is a Basic Notification With Icon")
//            you can enable an expandable notification by adding a style template with setStyle()
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("This is a Basic Notification With BigTextStyle")
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            manager.notify(1, builder.build())
        }

        findViewById<Button>(R.id.basicNotifyupper).setOnClickListener {
            createNotificationChannel()
            builder = NotificationCompat.Builder(this, CHANNEL_ID)
            builder.apply {
                setSmallIcon(R.drawable.baseline_settings_24)
                setContentTitle("Basic Notification Upper 8.0")
                setContentText("This is a Basic Notification With Icon")
                priority = NotificationCompat.PRIORITY_DEFAULT
            }
            with(NotificationManagerCompat.from(this)){
                notify(1,builder.build())
            }

        }

        findViewById<Button>(R.id.notifyBtn).setOnClickListener {
            val intent = Intent(this,RuntimePermissionStartActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE)
            val newIntent = Intent(this,AllTaskList::class.java).apply {
                action = Intent.ACTION_DEFAULT
                putExtra("msg",0)
            }
            val pendingIntentBroadCast = PendingIntent.getBroadcast(this,0,newIntent,PendingIntent.FLAG_IMMUTABLE)
            createNotificationChannel()
            builder = NotificationCompat.Builder(this, CHANNEL_ID)
            builder.apply {
                setSmallIcon(R.drawable.ic_launcher_foreground)
                setContentTitle("Tap On Notification")
                setContentText("This is a Basic Notification With Icon and Tap action")
                priority = NotificationCompat.PRIORITY_DEFAULT
                setContentIntent(pendingIntent)
                addAction(R.drawable.baseline_add_24,getString(R.string.app_name),pendingIntentBroadCast)
                setAutoCancel(true)
            }
            with(NotificationManagerCompat.from(this)){
                notify(1,builder.build())
            }
        }
        findViewById<Button>(R.id.notifyTap).setOnClickListener {
            createNotificationChannelImg()
            builder = NotificationCompat.Builder(this, CHANNEL_ID)
                builder.apply {
                    setSmallIcon(R.drawable.shape_half_up)
                    setContentTitle("BMW Car")
                    setContentText("Now this car For sale")
                    setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.carimg))
                    setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources,R.drawable.carimg))
                        .bigLargeIcon(null))

                }
            with(NotificationManagerCompat.from(this)){
                notify(1,builder.build())
            }
        }

        findViewById<Button>(R.id.fsn).setOnClickListener {
            val fullScreenIntent = Intent(this, MetarialUI::class.java)
            val fullScreenPendingIntent = PendingIntent.getActivity(
                this,
                0,
                fullScreenIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            createNotificationChannelImg()
            builder = NotificationCompat.Builder(this, CHANNEL_ID)
            builder.apply {
                setSmallIcon(R.drawable.shape_half_up)
                setContentTitle("Incoming Call")
                setContentText("(+91) 124487-47510")
                priority = NotificationCompat.PRIORITY_HIGH
                setCategory(NotificationCompat.CATEGORY_CALL)
                setFullScreenIntent(fullScreenPendingIntent, true)
            }
            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        }

        findViewById<Button>(R.id.fcn).setOnClickListener {
//            val notificationLayout = RemoteViews(applicationContext,R.layout.rv_userdata)
//            val notificationExpanded = RemoteViews(this,R.layout.custom_dialog)
//
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
    private fun createNotificationChannelImg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"Second Channel",NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "This description of Notification Img"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}