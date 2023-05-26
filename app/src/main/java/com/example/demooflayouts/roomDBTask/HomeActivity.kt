package com.example.demooflayouts.roomDBTask

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.demooflayouts.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase
    private lateinit var shardPref: SharedPreferences
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: MyTaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.task_rv)
        shardPref = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "taskDetail").build()
        var taskList : List<TaskDetails>

        GlobalScope.launch {
            taskList = appDatabase.taskDetailDao()
                .getAllTaskRecord(shardPref.getString("email", "").toString())
            if(taskList.isNotEmpty()){
            taskAdapter = MyTaskAdapter(taskList)
            recyclerView.adapter = taskAdapter
            taskAdapter.notifyDataSetChanged()
            }
        }


        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            with(shardPref.edit()) {
                remove("isLogin")
                commit()
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val personName = findViewById<TextView>(R.id.tv_name)
        personName.text = "${getString(R.string.HelloMsg)} ${shardPref.getString("name", "")}"

        findViewById<FloatingActionButton>(R.id.btn_add_data).setOnClickListener {
            addDataOnAlertBox()

        }
    }

    private fun addDataOnAlertBox() {
        val dialog = Dialog(this)
        dialog.apply {
            setContentView(R.layout.custom_alert_task_details)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val title = dialog.findViewById<TextView>(R.id.title_txt)
            val insertData = dialog.findViewById<Button>(R.id.btn_right)
            val cancelData = dialog.findViewById<Button>(R.id.btn_left)

            title.text = getString(R.string.add_data)
            insertData.text = getString(R.string.insert)
            cancelData.text = getString(R.string.cancel)

            val etTitle = dialog.findViewById<EditText>(R.id.et_task_title)
            val etDesc = dialog.findViewById<EditText>(R.id.et_task_desc)

            insertData.setOnClickListener {
                insertTask(etTitle, etDesc)
                Toast.makeText(this@HomeActivity, "Add", Toast.LENGTH_SHORT).show()

                dismiss()
            }
            cancelData.setOnClickListener {
                dismiss()
            }
        }.show()

    }

    private fun insertTask(etTitle: EditText, etDesc: EditText) {
        GlobalScope.launch {
            appDatabase.taskDetailDao().insertTask(
                TaskDetails(
                    0, etTitle.text.toString(), etDesc.text.toString(),
                    shardPref.getString("email", "").toString()
                )
            )
        }
        taskAdapter.notifyDataSetChanged()
    }
}