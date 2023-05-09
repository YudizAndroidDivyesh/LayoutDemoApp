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
import com.google.android.material.color.DynamicColors

class AllViewActivity : AppCompatActivity() {


  private  lateinit var etName : EditText
    private   lateinit var tvName : TextView
    private lateinit var btn : Button
    private lateinit var checkBox : CheckBox
    private lateinit var pgBar : ProgressBar
    private lateinit var rgBtn : RadioGroup
    private lateinit var imgV : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_view)
        DynamicColors.applyToActivitiesIfAvailable(applicationContext as Application)
        etName = findViewById(R.id.name)
        tvName = findViewById(R.id.nameWrite)
        btn = findViewById(R.id.subBtn)
        checkBox = findViewById(R.id.cb)
        pgBar = findViewById(R.id.progBarStyle)
        rgBtn = findViewById(R.id.rg)
        imgV = findViewById(R.id.imageView)

        var isProgress = true


        rgBtn.setOnCheckedChangeListener{

            _ , ischecked -> val radioBtn = findViewById<RadioButton>(ischecked)
            Toast.makeText(applicationContext, "${radioBtn.text}", Toast.LENGTH_SHORT).show()

            if(radioBtn.text == "Hide"){
                imgV.visibility = View.GONE
            }else{
                imgV.visibility = View.VISIBLE
            }
        }


        btn.setOnClickListener {
            if(checkBox.isChecked && tvName.text.isNotEmpty()) Toast.makeText(applicationContext, "apply", Toast.LENGTH_SHORT).show() else Toast.makeText(
                applicationContext,
                "Not Apply",
                Toast.LENGTH_SHORT
            ).show()

            if (isProgress){
                pgBar.visibility = View.VISIBLE
                isProgress = false
            }else{
                pgBar.visibility = View.GONE
                isProgress = true
            }


        }

        etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Toast.makeText(applicationContext, "beforeTextChanged", Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tvName.text = p0
            }

            override fun afterTextChanged(p0: Editable?) {
                Toast.makeText(applicationContext, "afterTextChanged", Toast.LENGTH_SHORT).show()
            }

        })
    }
}