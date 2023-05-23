package com.example.demooflayouts

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

open class MultiThreadingActivity : AppCompatActivity() {
    lateinit var normalThreadTv: TextView
    lateinit var asyncTv: TextView
    lateinit var coroutineResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_threading)
        normalThreadTv = findViewById(R.id.tv_normalThread)
//   Create a thread that adds 2 numbers and then set the result in a text view.





        findViewById<Button>(R.id.btn_normalThread).setOnClickListener {
            var result = 0
           try {
           val t =     Thread(Runnable {result =   23+ 23 })
               t.run()
               normalThreadTv.text = result.toString()

           }catch (e : Exception){
               e.printStackTrace()
           }

//            val handler = Handler()
//
//            Thread {
//                handler.post {
//                    val result = 1230 + 1541
//                    normalThreadTv.text = result.toString()
//                }
//            }.start()

        }

//        Create two threads to perform any operations on a number. Print the final result on the screen. (Use any mathematical operations)
//        Get a number from main thread
//        Perform operation in 1st thread
//        Perform another operation on the result using 2nd thread
//        Set the final result in a text view
        val innerThreadData = findViewById<TextView>(R.id.tv_threadOnThread)
        findViewById<Button>(R.id.btn_threadOnThread).setOnClickListener {
            val a = 2023
            val b = 2047
            val handle = Handler()
            Thread {
                val result = a + b
                handle.post {
                    innerThreadData.text = result.toString()
                }
            }.start()
        }


//        Use an async task to execute a loop and update the counter with each iteration.
        //    The counter value is to be displayed in the text view. When the count value reaches 10, cancel the async task.
        //     Display a toast after the async task is completed executing.
        asyncTv = findViewById(R.id.tv_async)
        findViewById<Button>(R.id.btn_async).setOnClickListener {
            val asyncTask = PerformArithmeticOperation(this)
            asyncTask.execute()
        }


//        Use coroutine to perform any complex operation and display the result in a text view.
        coroutineResult = findViewById(R.id.tv_coroutine)
        findViewById<Button>(R.id.btn_coroutine).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val time = Calendar.getInstance().timeInMillis
                showTime(time)
                println("Coroutine Start")
            }
            println("Coroutine Finish")
        }


    }

    private suspend fun showTime(time: Long) {
        delay(1000)
        coroutineResult.text = time.toString()
    }
}
class PerformArithmeticOperation(val context: MultiThreadingActivity) :
    AsyncTask<Int, Int, Int>() {
    override fun doInBackground(vararg p0: Int?): Int {
        var size = 0
        for (i in 0..15) {
            size = i
            publishProgress(size)
            if (isCancelled || i == 10)
                break

        }
        return size
    }

    override fun onCancelled() {
        super.onCancelled()
        Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        context.asyncTv.text = values[0].toString()

    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        display("completed")
    }

    private fun display(result: String) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
    }
}

