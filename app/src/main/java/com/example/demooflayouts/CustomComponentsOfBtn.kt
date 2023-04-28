package com.example.demooflayouts

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CustomComponentsOfBtn(context: Context, attrs: AttributeSet?) : LinearLayout(context,attrs){


    lateinit var tv_Title : TextView
    lateinit var Btn_txt : Button

    init {
        addCustomView(context, attrs)
    }

    fun addCustomView(context: Context, attrs: AttributeSet?){

        LayoutInflater.from(context).inflate(R.layout.custom_btn_progress,this,true)
        orientation = VERTICAL
        tv_Title = findViewById(R.id.tv_msg)
        Btn_txt = findViewById(R.id.progressBtn)

        attrs?.let {
            val typeArr : TypedArray =  context.obtainStyledAttributes(it,R.styleable.CustomComponentsOfBtn)
            val titleText =  typeArr.getString(R.styleable.CustomComponentsOfBtn_cust_btn)
            val txtBtn = typeArr.getString(R.styleable.CustomComponentsOfBtn_cust_txt)
            typeArr.recycle()
            tv_Title.text = titleText
            Btn_txt.text = txtBtn

        }



    }
}