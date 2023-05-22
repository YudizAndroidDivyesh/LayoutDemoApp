package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.demooflayouts.R

class ActionOnBroadCastActivity : AppCompatActivity() {

    private  var statusTv: TextView? = null
    lateinit var liveBatteryData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_on_broad_cast)

        statusTv = findViewById(R.id.status_battery_tv)
        liveBatteryData = findViewById(R.id.liveStatusOfBattery_tv)
        statusTv?.text = ""


           register(0)

        // registerReceiver(BroadCastReceiverClass(),Intent.ACTION_BATTERY_CHANGED)
    }

    fun register(percentageData: Int) {
        statusTv?.text = "Start"
        registerReceiver(batteryBroadCast, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        liveBatteryData.text = "Your battery percentage is 0%"

    }

    fun unregister() {
          statusTv?.text = "Stop"
        unregisterReceiver(batteryBroadCast)
    }


    val batteryBroadCast = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            Log.d("Action", context.toString())

            val percentageData = intent.getIntExtra("level", 0)
            Log.d("percentageData",percentageData.toString())
            liveBatteryData.text = "Your battery percentage is " + percentageData.toString() + "%"
            actionOnBroadCast(percentageData)
            Log.d("Status", intent.action.toString())
        }

    }

    fun actionOnBroadCast(percentageData: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BatteryAction", "BatteryChannel", NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This Is Battery BroadCast"

            //   val intent = Intent(this,ActionOnBroadCastActivity::class.java)
            // val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE)

            val startIntent =  Intent(this,BroadCastNotificationClass::class.java).setAction("Start")

            val stopIntent =  Intent(this,BroadCastNotificationClass::class.java).setAction("Stop")

            val layoutRemote = RemoteViews(packageName, R.layout.custom_expanded_notifiction)
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_start, PendingIntent.getBroadcast(
                    this,
                    0,
                    startIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_stop, PendingIntent.getBroadcast(
                    this,
                    0,
                    stopIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(this, "BatteryAction")
            builder.apply {
                setSmallIcon(R.drawable.baseline_battery_charging_full_24)
                setContentTitle("Activity Second")
                priority = NotificationCompat.PRIORITY_HIGH
                //  setContentIntent(pendingIntent)
                setAutoCancel(true)
                layoutRemote.setTextViewText(
                    R.id.statusCheck_tv,
                    getString(R.string.batteryPercentage) + " " + percentageData
                )
                setCustomBigContentView(layoutRemote)
            }
            manager.notify(0, builder.build())
        }

    }
}
class BroadCastNotificationClass : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("Action", intent.toString())

//        val percentageData = intent.getIntExtra("level", 0)
//        Log.d("percentageData",percentageData.toString())
//        actionOnBroadCast.register(percentageData)
//        actionOnBroadCast.actionOnBroadCast(percentageData)
//        Log.d("Status", intent.action.toString())

        // actionOnBroadCast.liveBatteryData.text = "Your battery percentage is "+percentageData.toString()+"%"
        try {
            val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

            when (intent.action) {
                "Start" -> {
                    //  register(percentageData)
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
                    Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

}
