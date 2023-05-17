package com.example.demooflayouts.workManager

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {


        val imageUrl = inputData.getString("imgUrl")
        Log.d("Img", imageUrl.toString())
        if (imageUrl == null) return Result.failure()
        val bitmap = imageUrl?.let { downloadImg(it) }
        Log.d("ImageBitmap", bitmap.toString())
        if (bitmap != null) {
            saveFileToStorage(bitmap)
        }
        return Result.success()

    }

    private fun downloadImg(imgUri: String?): Bitmap? {
        for (i in 0..20) {
            val pro = i * 5
            setProgressAsync(workDataOf("progress" to i * 5))
            Log.d("Progress", pro.toString())
            Thread.sleep(2000)
        }
        return try {
            val url = URL(imgUri)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveFileToStorage(bitmap: Bitmap) {
        val fileName = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        var imgUri: Uri?

        val contentResolver = applicationContext.contentResolver
        val contentValue = ContentValues()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValue.apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/*")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            imgUri =
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue)
            fos = imgUri?.let { contentResolver.openOutputStream(it) }
            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}