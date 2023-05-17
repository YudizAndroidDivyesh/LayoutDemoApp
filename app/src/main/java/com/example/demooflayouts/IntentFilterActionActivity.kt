package com.example.demooflayouts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class IntentFilterActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_filter_action)
        val url = findViewById<EditText>(R.id.et_url)
        if(intent?.action == Intent.ACTION_SEND){
           val imgUri  = intent.extras?.get(Intent.EXTRA_STREAM) as Uri
//            val bundle = Uri.parse(intent.extras?.get(Intent.EXTRA_STREAM).toString())
           // val bundle = Uri.parse(i.toString())

            findViewById<ImageView>(R.id.foreign_img).setImageURI(imgUri)
        }
        findViewById<Button>(R.id.redirect_btn).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
              //  data = Uri.parse("https://www.google.com/search?q="+url.text)
                data = Uri.parse("https://www."+url.text+".com")
            }
            startActivity(Intent.createChooser(intent,"Select Any One"))
        }
    }
}