package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class CustomComponents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_components)
        val customBtn = findViewById<CustomComponentsOfBtn>(R.id.custView)
         customBtn.setOnClickListener {
              customBtn.progressVisible()
              customBtn.progressStart()
            }
    }
}