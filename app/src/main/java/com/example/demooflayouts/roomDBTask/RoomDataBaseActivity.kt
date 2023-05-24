package com.example.demooflayouts.roomDBTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demooflayouts.R

class RoomDataBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_data_base)

       // val progressBar = findViewById<ProgressBar>(R.id.splash_progressBar)

        Thread{
            Thread.sleep(2000)
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }.start()
    }
}