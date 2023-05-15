package com.example.demooflayouts.broadCastReceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demooflayouts.R

class BroadCastPercentageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_percentage)

        BroadCastReceiverClass()


    }
}