package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MultiThreadingActivity : AppCompatActivity() {
lateinit var normalThreadTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_threading)

       normalThreadTv = findViewById(R.id.tv_normalThread)
//   Create a thread that adds 2 numbers and then set the result in a text view.
        findViewById<Button>(R.id.btn_normalThread).setOnClickListener {
            var result = 0
            Thread{
               result = 100 + 100000
          }.start()
            normalThreadTv.text = result.toString()
        }

//        Create two threads to perform any operations on a number. Print the final result on the screen. (Use any mathematical operations)
//        Get a number from main thread
//        Perform operation in 1st thread
//        Perform another operation on the result using 2nd thread
//        Set the final result in a text view
        val innerThreadData = findViewById<TextView>(R.id.tv_threadOnThread)
                findViewById<Button>(R.id.btn_threadOnThread).setOnClickListener {
                    val a= 2023
                    val b = 2047
            val handle = Handler()
            Thread{
               val result = a +b
                handle.post {
                    innerThreadData.text = result.toString()
                }
            }.start()
        }
    }
}