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
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {


        val imageUrl = inputData.getString("imgUrl")
        Log.d("Img", imageUrl.toString())
        if (imageUrl == null) return Result.failure()
        downloadImg(imageUrl)
//        val bitmap = imageUrl?.let { }
//        Log.d("ImageBitmap", bitmap.toString())
        return Result.success()

    }

    private fun downloadImg(imgUri: String?){

        var input : InputStream?
        var count = 0
        var outPut: OutputStream?

        try {
             val url = URL(imgUri)
             val connection = url.openConnection() as HttpURLConnection
             connection.doInput = true
             val dataSize = connection.contentLength

             Log.d("Size", connection.contentLength.toString())
             connection.connect()
             input = connection.inputStream
            outPut = FileOutputStream("/storage/emulated/0/DCIM/wikiImage.jpg")

             val data = ByteArray(2048)
             var total = 0
            count = input.read(data)
            Log.d("Count",count.toString())
            while (count != -1){
                 total += count
                setProgressAsync(workDataOf("progress" to (total * 100) / dataSize))
                outPut.write(data,0,count)
                Log.d("Count",data.toString())
                count = input.read(data)
            }
            outPut.run {
                 flush()
                 close()
             }
             input.close()
//             input = connection.inputStream
//             for (i in 1..   connection.contentLength/2024) {
//                 Log.d("Progress",  "Sending Progress: $i")
//                setProgressAsync(workDataOf("progress" to i))
//                 Thread.sleep(100)
//                }
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    private fun saveFileToStorage(bitmap: Bitmap) {
        val fileName = "${System.currentTimeMillis()}.jpg"
        val fos: OutputStream?
        val imgUri: Uri?

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
//            fos?.use {
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
//              //  Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}