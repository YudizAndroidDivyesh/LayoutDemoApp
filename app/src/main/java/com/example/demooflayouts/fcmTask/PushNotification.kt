package com.example.demooflayouts.fcmTask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demooflayouts.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val CHANNEL_ID = "FCM Notification"

class PushNotification : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.i("TAG_TOKEN",token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
         if(message.notification != null){

        showNotification(message.notification?.title,message.notification?.body)
         }
        super.onMessageReceived(message)


    }

    private fun showNotification(title: String?, body: String?) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"Push Notification",NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        builder.apply {
            setContentTitle(title)
            setContentText(body)
            setSmallIcon(R.drawable.baseline_settings_24)
            setAutoCancel(true)
        }
        NotificationManagerCompat.from(this).notify(1,builder.build())
    }


}