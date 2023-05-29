package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.demooflayouts.R


class ActionOnBroadCastActivity : AppCompatActivity() {

    var isStoped = false
    var statusTv: TextView? = null
    lateinit var liveBatteryData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_on_broad_cast)

        statusTv = findViewById(R.id.status_battery_tv)
        liveBatteryData = findViewById(R.id.liveStatusOfBattery_tv)
        statusTv?.text = ""


           //register(0)

        actionOnBroadCast(this)
        // registerReceiver(BroadCastReceiverClass(),IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    fun register(percentageData: Int) {
        statusTv?.text = "Start"
       // this.registerReceiver(batteryBroadCast, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        liveBatteryData.text = "Your battery percentage is $percentageData%"

    }

    fun actionOnBroadCast(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BatteryAction", "BatteryChannel", NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This Is Battery BroadCast"

            //   val intent = Intent(this,ActionOnBroadCastActivity::class.java)
            // val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

            val startIntent =  Intent(context,BroadCastReceiverClass::class.java).setAction("Start")


            val stopIntent =  Intent(context,BroadCastReceiverClass::class.java).setAction("Stop")


            val layoutRemote = RemoteViews(context.packageName, R.layout.custom_expanded_notifiction)
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_start, PendingIntent.getBroadcast(
                    context,
                    0,
                    startIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_stop, PendingIntent.getBroadcast(
                    context,
                    0,
                    stopIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(context, "BatteryAction")
            builder.apply {
                setSmallIcon(R.drawable.baseline_battery_charging_full_24)
                setContentTitle("Activity Second")
                priority = NotificationCompat.PRIORITY_HIGH
                //  setContentIntent(pendingIntent)
                setAutoCancel(true)
                layoutRemote.setTextViewText(
                    R.id.statusCheck_tv,
                    context.getString(R.string.batteryPercentage) + " Unregister "
                )
                setCustomBigContentView(layoutRemote)
            }
            manager.notify(0, builder.build())
        }

    }


    fun unregister() {
          statusTv?.text = "Stop"
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show()
        //unregisterReceiver(batteryBroadCast)
    }
}



