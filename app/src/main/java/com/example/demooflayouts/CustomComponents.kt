package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CustomComponents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_components)
        val customBtn = findViewById<CustomComponentsOfBtn>(R.id.custView)
//         customBtn.setOnClickListener {
//              customBtn.progressVisible()
//              customBtn.progressStart()
//            }

        customBtn.setCustomClick(object : CustomComponentsOfBtn.Click {
            override fun onButtonClick(v: View) {
                customBtn.progressVisible()
                customBtn.progressStart()
                Toast.makeText(this@CustomComponents, "Clicked", Toast.LENGTH_SHORT).show()
            }

        })
    }
}