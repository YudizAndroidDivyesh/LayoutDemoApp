package com.example.demooflayouts.broadCastReceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.demooflayouts.R

class BroadCastReceiverClass : BroadcastReceiver() {
    var isRegister = false
    var unregister = false
    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("Action", intent.toString())
        Log.d("Context", context.toString())
        val percentageData = intent.getIntExtra("level", 0)
        if(isRegister) actionOnBroadCast(percentageData, MyApp.context!!)
        try {
            when (intent.action) {
                "Start" -> {
                    Thread{
                      MyApp.context!!.registerReceiver(this,IntentFilter(Intent.ACTION_BATTERY_CHANGED))
                    }.start()
                    //startRegister(intent,true)
                    isRegister = true
                    unregister = true
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
                    isRegister = false
                    if(unregister) {
                        try {

                            Handler().postDelayed({
                                MyApp.context!!.unregisterReceiver(this)
                            }, 10000)
                            Toast.makeText(MyApp.context!!, "Stop", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        unregister = false
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
    fun actionOnBroadCast(percentageData: Int,context: Context) {
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
                    context.getString(R.string.batteryPercentage) + " " + percentageData
                )
                setCustomBigContentView(layoutRemote)
            }
            manager.notify(0, builder.build())
        }
    }
}