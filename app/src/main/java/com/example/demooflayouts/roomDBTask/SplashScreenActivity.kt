package com.example.demooflayouts.roomDBTask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demooflayouts.R

const val sharedPrefFile = "UserLoginDetails"
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

       // val progressBar = findViewById<ProgressBar>(R.id.splash_progressBar)

        val shredPref = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        Thread{
            Thread.sleep(2000)
            if (shredPref.contains("isLogin")){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }.start()



    }
}