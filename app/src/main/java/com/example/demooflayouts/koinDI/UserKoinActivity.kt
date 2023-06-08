package com.example.demooflayouts.koinDI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.demooflayouts.R
import org.koin.android.ext.android.inject

class UserKoinActivity : AppCompatActivity() {
  //  val model : MyViewModel by viewModels()
    val mData : MyUtil by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_koin)
    //    println(model.doNetworkCall())
            mData.saveName(this,"Dev")
       findViewById<TextView>(R.id.koin_tv).text = mData.getName(this)
    }
}
