package com.example.demooflayouts

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible

class RuntimePermissionStartActivity : AppCompatActivity() {

    val a = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == 0){
            val result = it.data
            findViewById<TextView>(R.id.msg).text = result?.getStringExtra("str").toString()
        }
       else{
            val imageView = findViewById<ImageView>(R.id.galleryImg)
            imageView.setImageURI(it.data?.data)
            imageView.visibility = View.VISIBLE
            Toast.makeText(this, "Img", Toast.LENGTH_SHORT).show()
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
       if (VERSION.SDK_INT >= VERSION_CODES.M){
           if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
               val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
               requestPermissions(permissions,2)
           }else{
               pickImageFromGallery()
           }
       }else{
           pickImageFromGallery()
       }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        a.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            pickImageFromGallery()
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}