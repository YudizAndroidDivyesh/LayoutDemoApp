package com.example.demooflayouts.workManager

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {


       val imageUrl = inputData.getString("imgUrl")

        if (imageUrl == null) return Result.failure()

        downloadImg(imageUrl)

        return Result.success()

    }

    private fun downloadImg(imgUri: String?) {
       for (i in 0..20){
           val pro = i * 5
           setProgressAsync(workDataOf("progress" to i * 5))
           Log.d("Img",i.toString())
           Thread.sleep(2000)
       }


    }
}