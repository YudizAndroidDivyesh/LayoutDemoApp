package com.example.demooflayouts.roomDBTask

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), MyTaskAdapter.OnItemClickListener {
    private lateinit var appDatabase: AppDatabase
    private lateinit var shardPref: SharedPreferences
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.task_rv)
        shardPref = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        appDatabase = AppDatabase.getDatabase(this)

        val taskAdapter = MyTaskAdapter(this)


        appDatabase.taskDetailDao().getAllTaskRecord(shardPref.getString("email", "").toString())
            .observe(this, Observer { user ->
                taskAdapter.setData(user)
            })
        recyclerView.adapter = taskAdapter
        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            logoutAlert()
        }

        val personName = findViewById<TextView>(R.id.tv_name)
        personName.text = "${getString(R.string.HelloMsg)} ${shardPref.getString("name", "")}"

        findViewById<FloatingActionButton>(R.id.btn_add_data).setOnClickListener {
            addDataOnAlertBox()

        }
    }

    private fun logoutAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("RoomDB App")
            setMessage("Do you want to Logout from RoomDB App")
            setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, _ ->
                with(shardPref.edit()) {
                    remove("isLogin")
                    apply()
                    val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
            })
            setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
        }.show()
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
                if (isValidate(etTitle, etDesc)) {
                    insertTask(etTitle, etDesc)
                    Toast.makeText(this@HomeActivity, "Add", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }
            cancelData.setOnClickListener {
                dismiss()
            }
        }.show()

    }

    private fun isValidate(title: EditText, desc: EditText): Boolean {
        if (title.text.isEmpty() && desc.text.isEmpty()) {
            Toast.makeText(this, "Empty Field not allowed", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
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
    }

    override fun onClick(position: Int, taskList: List<TaskDetails>) {
        val dialog = Dialog(this)
        dialog.apply {
            setContentView(R.layout.custom_alert_task_details)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val title = dialog.findViewById<TextView>(R.id.title_txt)
            val updateData = dialog.findViewById<Button>(R.id.btn_right)
            val deleteData = dialog.findViewById<Button>(R.id.btn_left)

            title.text = getString(R.string.delete_update_data)
            updateData.text = getString(R.string.update)
            deleteData.text = getString(R.string.delete)

            val etTitle = dialog.findViewById<EditText>(R.id.et_task_title)
            val etDesc = dialog.findViewById<EditText>(R.id.et_task_desc)

            val taskRecord = taskList[position]
            etTitle.setText(taskRecord.taskTitle)
            etDesc.setText(taskRecord.taskDesc)

            updateData.setOnClickListener {

                if (isValidate(etTitle, etDesc)) {

                    GlobalScope.launch(Dispatchers.IO) {
                        appDatabase.taskDetailDao().updateTask(
                            etTitle.text.toString(),
                            etDesc.text.toString(),
                            taskRecord.taskId
                        )
                    }
                    dismiss()
                }

            }
            deleteData.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    appDatabase.taskDetailDao().deleteTask(taskRecord.taskId)
                }
                dismiss()
            }
        }.show()
    }
}