package com.example.demooflayouts

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class RuntimePermissionStartActivity : AppCompatActivity() {

    val a = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == 0){
            val result = it.data
            findViewById<TextView>(R.id.msg).text = result?.getStringExtra("str").toString()
        }
        if (it.resultCode == 2){
            val result : Intent? = it.data
//            findViewById<ImageView>(R.id.galleryImg).setImageBitmap(result)
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
            askPermission()
        }

    }

    fun askPermission(){
        //check runtime permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                //Permission Denied

            }
        }
    }
}