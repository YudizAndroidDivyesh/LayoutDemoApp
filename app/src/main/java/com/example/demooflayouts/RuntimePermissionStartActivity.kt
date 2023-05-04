package com.example.demooflayouts

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
class RuntimePermissionStartActivity : AppCompatActivity() {

    val a = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == 0){
            val result = intent.getStringExtra("str")
            findViewById<TextView>(R.id.msg).text = result
            Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime_permission_start)
        
        findViewById<Button>(R.id.nextScreen).setOnClickListener {
            val intent = Intent(this,nextActivity::class.java)
            a.launch(intent)
        }

        findViewById<Button>(R.id.mediaPermission).setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
        }

    }
}