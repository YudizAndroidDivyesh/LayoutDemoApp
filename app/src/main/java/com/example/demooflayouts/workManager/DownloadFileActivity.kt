package com.example.demooflayouts.workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.*
import com.example.demooflayouts.R

class DownloadFileActivity : AppCompatActivity() {

    val mWorkManager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_file)

        // val constrains = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        val inputData = Data.Builder()
            .putString("data","Hello")
            .build()
        // Created a Work Request
        val workRequest = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            // .setConstraints(constrains)
            .setInputData(inputData)
            .build()

        findViewById<Button>(R.id.DownLoadBtn).setOnClickListener {
             // Submit the WorkRequest to the system
            WorkManager.getInstance(this).enqueue(workRequest)
        }



    }
}