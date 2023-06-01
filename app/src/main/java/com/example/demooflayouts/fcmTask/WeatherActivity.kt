package com.example.demooflayouts.fcmTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.messaging.subscribeToTopic("weather").addOnCompleteListener {
            task -> var msg = "Subscribe"
            if (!task.isSuccessful){
                msg = "Subscribe Failed"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }
}