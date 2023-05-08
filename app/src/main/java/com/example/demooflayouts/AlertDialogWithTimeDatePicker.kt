package com.example.demooflayouts

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AlertDialogWithTimeDatePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_with_time_date_picker)

        val alrt_btn = findViewById<Button>(R.id.DialogBox)
        alrt_btn.setOnClickListener {
            alertdialogclass(this).show()
        }

    }
}