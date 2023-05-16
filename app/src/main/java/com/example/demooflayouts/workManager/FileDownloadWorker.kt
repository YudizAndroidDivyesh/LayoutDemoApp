package com.example.demooflayouts.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        return Result.success()
    }

    private fun downloadImg(imgUri: String) {
        for (i in 0..10){
            Thread.sleep(2000)
            Log.d("WorkManager",imgUri)
        }
    }
}