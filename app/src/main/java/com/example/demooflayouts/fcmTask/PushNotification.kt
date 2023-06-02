package com.example.demooflayouts.fcmTask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demooflayouts.MetarialUI
import com.example.demooflayouts.R
import com.example.demooflayouts.SaveFileActivity
import com.example.demooflayouts.retrofitTask.ProductListActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val CHANNEL_ID = "FCM Notification"

class PushNotification : FirebaseMessagingService() {



    override fun onNewToken(token: String) {
        Log.i("TAG_TOKEN", token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {

            val setActivity = checkValue(message)

            showNotification(message.notification?.title, message.notification?.body, setActivity)
            message.data["onclick"]?.let { Log.d("TAG_data", it) }
        }
        super.onMessageReceived(message)


    }

    private fun checkValue(s: RemoteMessage): PendingIntent? {
        when (s.data["onclick"]) {
            "WeatherActivity" -> {
                val intent = Intent(applicationContext, WeatherActivity::class.java)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.action = Intent.ACTION_MAIN
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
            "MetarialUI" -> {
                val intent = Intent(applicationContext, MetarialUI::class.java)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.action = Intent.ACTION_MAIN
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
            "ProductListActivity" -> {
                val intent = Intent(applicationContext, ProductListActivity::class.java)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.action = Intent.ACTION_MAIN
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                intent.putExtra("push","product")
                return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
            "SaveFileActivity" -> {
                val intent = Intent(applicationContext, SaveFileActivity::class.java)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.action = Intent.ACTION_MAIN
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
        }
        return null
    }

    private fun showNotification(
        title: String?,
        body: String?,
        pendingIntent: PendingIntent?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Push_Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        builder.apply {
            setContentTitle(title)
            setContentText(body)
            setSmallIcon(R.drawable.baseline_settings_24)
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }


}