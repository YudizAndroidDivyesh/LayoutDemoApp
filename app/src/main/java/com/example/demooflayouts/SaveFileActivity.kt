package com.example.demooflayouts

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class SaveFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_file)


        val image = findViewById<ImageView>(R.id.iv_car)


        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            checkPermission()
        }
        findViewById<Button>(R.id.btn_save).setOnClickListener {

            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val alertDialog = AlertDialog.Builder(this)
                    val dialog = alertDialog.create()
                    alertDialog.apply {
                        setTitle("Permission Denied")
                        setMessage("You have to give permission")
                        setPositiveButton("Ok"){
                                _,_ ->  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package",packageName,null)
                            intent.data = uri
                            startActivity(intent)
                        }
                        setNegativeButton("Cancel"){
                                _,_ -> dialog.dismiss()
                        }
                    }.show()
                }else{
                    val bitmap = getImgScreenShot(image)
                    if(bitmap != null){
                        saveFileToStorage(bitmap)

                    }
                }
            }else{
                val bitmap = getImgScreenShot(image)
                if(bitmap != null){
                    saveFileToStorage(bitmap)

                }
            }

        }

        findViewById<Button>(R.id.btn_save_share).setOnClickListener {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val alertDialog = AlertDialog.Builder(this)
                    val dialog = alertDialog.create()
                    alertDialog.apply {
                        setTitle("Permission Denied")
                        setMessage("You have to give permission")
                        setPositiveButton("Ok"){
                                _,_ ->  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package",packageName,null)
                            intent.data = uri
                            startActivity(intent)
                        }
                        setNegativeButton("Cancel"){
                                _,_ -> dialog.dismiss()
                        }
                    }.show()
                }else{
                    val bitmap = getImgScreenShot(image)
                    if(bitmap != null){
                        val uri = saveFileToStorage(bitmap)
                        Log.d("Path",uri.toString())
                        shareImg(uri)
                    }
                }
            }else{
                val bitmap = getImgScreenShot(image)
                if(bitmap != null){
                    val uri = saveFileToStorage(bitmap)
                    Log.d("Path",uri.toString())
                    shareImg(uri)
                }
            }
        }

    }

    private fun checkPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

    }

    private fun shareImg(path: Uri?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM,path)
        }
        startActivity(Intent.createChooser(shareIntent,"Share Via"))
    }

    private fun saveFileToStorage(bitmap : Bitmap): Uri? {
        val fileName = "${System.currentTimeMillis()}.jpg"
        var fos : OutputStream? = null
       var imgUri : Uri? = null

        val contentResolver = contentResolver
        val contentValue = ContentValues()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            contentValue.apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME,fileName)
                put(MediaStore.MediaColumns.MIME_TYPE,"image/*")
                put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES)
            }
            imgUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValue)
            fos = imgUri?.let { contentResolver.openOutputStream(it) }
        }else {
            // These for devices running on android < Q
            imgUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValue)
            fos = imgUri?.let { contentResolver.openOutputStream(it) }
            Log.d("Path",imgUri.toString())
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
        return imgUri
    }

    private fun getImgScreenShot(image: ImageView): Bitmap? {
        var screenshot : Bitmap? = null
        try {

            screenshot = Bitmap.createBitmap(image.width,image.height,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            image.draw(canvas)

        }catch (e : Exception){
            Log.d("Images Not Captured : ",e.message.toString())
        }
        return screenshot
    }
}