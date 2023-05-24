package com.example.demooflayouts.roomDBTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.demooflayouts.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.tv_login_sign_up).setOnClickListener{
            val intent = Intent(this,CreateAccountActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}