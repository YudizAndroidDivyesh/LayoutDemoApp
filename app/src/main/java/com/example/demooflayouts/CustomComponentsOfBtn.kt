package com.example.demooflayouts

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

class CustomComponentsOfBtn(context: Context, attrs: AttributeSet?) : LinearLayout(context,attrs){


    lateinit var tv_Title : TextView
    lateinit var Btn_txt : TextView
    lateinit var frameLayoutBtn : FrameLayout
    lateinit var progressBar  : ProgressBar

    init {
        addCustomView(context, attrs)

    }

    fun addCustomView(context: Context, attrs: AttributeSet?){
        LayoutInflater.from(context).inflate(R.layout.custom_btn_progress,this,true)
        orientation = VERTICAL

        frameLayoutBtn = findViewById(R.id.frameBtn)
        tv_Title = findViewById(R.id.tv_msg)
        Btn_txt = findViewById(R.id.progressBtn)
        progressBar = findViewById(R.id.pgNow)



        attrs?.let {
            val typeArr : TypedArray =  context.obtainStyledAttributes(it,R.styleable.CustomComponentsOfBtn)
            val titleText =  typeArr.getString(R.styleable.CustomComponentsOfBtn_cust_btn)
            val txtBtn = typeArr.getString(R.styleable.CustomComponentsOfBtn_cust_txt)
            typeArr.recycle()
            tv_Title.text = titleText
            Btn_txt.text = txtBtn
        }
        frameLayoutBtn.setOnClickListener {
//                if(isProgress){
//                    Btn_txt.visibility = View.GONE
//                    progressBar.visibility = View.VISIBLE
//                    isProgress = false
//                }else{
//                    Btn_txt.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    isProgress = true
//                }
            Btn_txt.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            handler.postDelayed( {
                Btn_txt.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            },5000)

        }
    }
}