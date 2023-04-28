package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CustomComponents : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_components)

        val setdata = findViewById<CustomComponentsOfBtn>(R.id.custView)
//        setdata.tv_Title.text = "Click On Button"
//        setdata.Btn_txt.text = "click Here"
    }
}