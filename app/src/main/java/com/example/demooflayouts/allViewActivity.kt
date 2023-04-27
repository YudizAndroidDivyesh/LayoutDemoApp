package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged

class allViewActivity : AppCompatActivity() {


    lateinit var et_Name : EditText
    lateinit var tv_Name : TextView
    lateinit var btn : Button
    lateinit var checkBox : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_view)

        et_Name = findViewById(R.id.name)
        tv_Name = findViewById(R.id.nameWrite)
        btn = findViewById(R.id.subBtn)
        checkBox = findViewById(R.id.cb)



        btn.setOnClickListener {
            if(checkBox.isChecked && tv_Name.text.isNotEmpty()) Toast.makeText(applicationContext, "apply", Toast.LENGTH_SHORT).show() else Toast.makeText(
                applicationContext,
                "Not Apply",
                Toast.LENGTH_SHORT
            ).show()
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