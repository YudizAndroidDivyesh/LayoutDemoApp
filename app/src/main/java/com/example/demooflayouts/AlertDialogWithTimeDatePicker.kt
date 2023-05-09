package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AlertDialogWithTimeDatePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_with_time_date_picker)

        val tvData = findViewById<TextView>(R.id.tv_data)
        val alrt_btn = findViewById<Button>(R.id.DialogBox)
        alrt_btn.setOnClickListener {
            alertdialogclass(this,tvData).show()

        }

    }
}