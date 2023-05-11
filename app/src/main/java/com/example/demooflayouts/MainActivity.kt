package com.example.demooflayouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.demooflayouts.AllLayouts.ContrainsLayoutActivity
import com.example.demooflayouts.AllLayouts.FrameLayoutActivity
import com.example.demooflayouts.AllLayouts.LinearLayoutActivity
import com.example.demooflayouts.AllLayouts.RelativeLayputActivity

class MainActivity : AppCompatActivity() {

    private lateinit var profileTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileTv = findViewById(R.id.tvProfile)

        profileTv.setOnClickListener {
            startActivity(Intent(this,ProfileScreen::class.java))
        }


        findViewById<Button>(R.id.llact).setOnClickListener {
            startActivity(Intent(this,LinearLayoutActivity::class.java))
        }
        findViewById<Button>(R.id.rlact).setOnClickListener {
            startActivity(Intent(this,RelativeLayputActivity::class.java))

        }
        findViewById<Button>(R.id.csAct).setOnClickListener {
            startActivity(Intent(this,ContrainsLayoutActivity::class.java))
        }
        findViewById<Button>(R.id.FrameAct).setOnClickListener {
            startActivity(Intent(this,FrameLayoutActivity::class.java))
        }



    }
}