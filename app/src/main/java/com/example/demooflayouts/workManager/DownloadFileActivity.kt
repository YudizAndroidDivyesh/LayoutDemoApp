package com.example.demooflayouts.workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.work.*
import com.example.demooflayouts.R

class DownloadFileActivity : AppCompatActivity() {

    val mWorkManager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        val workManager = WorkManager.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_file)

        val progressBar = findViewById<ProgressBar>(R.id.ImgProgressBar)

        findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            mWorkManager.cancelAllWorkByTag("demo")
        }

        // val constrains = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        val inputData = Data.Builder()
            .putString("imgUrl","https://images.all-free-download.com/images/graphicwebp/nature_picture_elegant_contrast_closeup_mushroom_6931741.webp")
            .build()

        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)

        // Created a Work Request
        val workRequest = OneTimeWorkRequestBuilder<FileDownloadWorker>()
             .setConstraints(constrains.build())
            .setInputData(inputData)
            .addTag("demo")
            .build()


        findViewById<Button>(R.id.DownLoadBtn).setOnClickListener {
             // Submit the WorkRequest to the system
            WorkManager.getInstance(this).enqueue(workRequest)
        }

        val status = findViewById<TextView>(R.id.downLoadStatus)

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this){
            if (it != null){
                val state = it.state
                status.append(state.toString()+"\n")
            }
        }

    }
}