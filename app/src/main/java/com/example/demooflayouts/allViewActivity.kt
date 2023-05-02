package com.example.demooflayouts

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.color.DynamicColors

class allViewActivity : AppCompatActivity() {


    lateinit var et_Name : EditText
    lateinit var tv_Name : TextView
    lateinit var btn : Button
    lateinit var checkBox : CheckBox
    lateinit var pgBar : ProgressBar
    lateinit var rgBtn : RadioGroup
    lateinit var imgV : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_view)
        DynamicColors.applyToActivitiesIfAvailable(applicationContext as Application)
        et_Name = findViewById(R.id.name)
        tv_Name = findViewById(R.id.nameWrite)
        btn = findViewById(R.id.subBtn)
        checkBox = findViewById(R.id.cb)
        pgBar = findViewById(R.id.progBarStyle)
        rgBtn = findViewById(R.id.rg)
        imgV = findViewById(R.id.imageView)

        var isProgrss = true


        rgBtn.setOnCheckedChangeListener{

            group , ischecked -> val radioBtn = findViewById<RadioButton>(ischecked)
            Toast.makeText(applicationContext, "${radioBtn.text}", Toast.LENGTH_SHORT).show()

            if(radioBtn.text == "Hide"){
                imgV.visibility = View.GONE
            }else{
                imgV.visibility = View.VISIBLE
            }
        }


        btn.setOnClickListener {
            if(checkBox.isChecked && tv_Name.text.isNotEmpty()) Toast.makeText(applicationContext, "apply", Toast.LENGTH_SHORT).show() else Toast.makeText(
                applicationContext,
                "Not Apply",
                Toast.LENGTH_SHORT
            ).show()

            if (isProgrss){
                pgBar.visibility = View.VISIBLE
                isProgrss = false
            }else{
                pgBar.visibility = View.GONE
                isProgrss = true
            }


        }

        et_Name.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Toast.makeText(applicationContext, "beforeTextChanged", Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tv_Name.text = p0
            }

            override fun afterTextChanged(p0: Editable?) {
                Toast.makeText(applicationContext, "afterTextChanged", Toast.LENGTH_SHORT).show()
            }

        })
    }
}