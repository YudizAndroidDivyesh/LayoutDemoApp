package com.example.demooflayouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class nextActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val name_et = findViewById<EditText>(R.id.et_name)
        val back_btn = findViewById<Button>(R.id.backBtn)

        back_btn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("str",name_et.text.toString())
            setResult(0,intent)
            finish()
        }


    }
}