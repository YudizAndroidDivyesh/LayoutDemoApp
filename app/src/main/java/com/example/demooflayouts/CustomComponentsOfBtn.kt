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

    private lateinit var Btn_txt : TextView
    private lateinit var frameLayoutBtn : FrameLayout
    private lateinit var progressBar  : ProgressBar
    private var click : Click? = null
    init {
        addCustomView(context, attrs)

    }

    fun progressVisible(){
        Btn_txt.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }
    fun progressStart(){
        handler.postDelayed( {
            Btn_txt.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        },5000)

    }

    private fun addCustomView(context: Context, attrs: AttributeSet?){
        LayoutInflater.from(context).inflate(R.layout.custom_btn_progress,this,true)
        orientation = VERTICAL

        frameLayoutBtn = findViewById(R.id.frameBtn)
        Btn_txt = findViewById(R.id.progressBtn)
        progressBar = findViewById(R.id.pgNow)

        attrs?.let {
            val typeArr : TypedArray =  context.obtainStyledAttributes(it,R.styleable.CustomComponentsOfBtn)
            val txtBtn = typeArr.getString(R.styleable.CustomComponentsOfBtn_cust_txt)
            typeArr.recycle()
            Btn_txt.text = txtBtn
        }

        setOnClickListener {
            click?.onButtonClick(it)

        }
//        frameLayoutBtn.setOnClickListener {
//                if(isProgress){
//                    Btn_txt.visibility = View.GONE
//                    progressBar.visibility = View.VISIBLE
//                    isProgress = false
//                }else{
//                    Btn_txt.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    isProgress = true
//                }
    //}
    }

    fun setCustomClick(c : Click){
        click = c
    }

    interface Click{
        fun onButtonClick(v : View)
    }
}