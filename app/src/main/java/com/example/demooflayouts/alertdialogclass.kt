package com.example.demooflayouts

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.time.Year
import java.util.Calendar
import kotlin.math.min

class alertdialogclass(context : Context) : Dialog(context) {

    init {
        setContentView(R.layout.custom_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val fromDate = findViewById<EditText>(R.id.fromDate_et)
        val toDate = findViewById<EditText>(R.id.toDate_et)
        val selectDate = findViewById<EditText>(R.id.selectTime_Et)
        val calender = Calendar.getInstance()
        val datePick = DatePickerDialog(context,{
            _,year,month,dayOfMonth ->
        },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH))
        val timePick = TimePickerDialog(context,TimePickerDialog.OnTimeSetListener{
            _,hourOfDay,minute -> selectDate.setText("$hourOfDay $minute")
        },12,0,false)
        fromDate.setOnClickListener{
            DatePickerDialog(context,{
                    _,year,month,dayOfMonth -> fromDate.setText("$year / ${month+1} / $dayOfMonth")
            },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH))
                .show()
        }
        toDate.setOnClickListener {
            datePick.show()
        }
        selectDate.setOnClickListener {
            timePick.show()
        }

        val submit = findViewById<Button>(R.id.submit_btn)
        submit.setOnClickListener {
            if(checkValidation(fromDate.text.toString(),toDate.text.toString(),selectDate.text.toString())){
                Toast.makeText(context, "$fromDate , $toDate , $selectDate", Toast.LENGTH_SHORT).show()
            }

        }
        val cancel = findViewById<Button>(R.id.cancel_btn)
        cancel.setOnClickListener {
            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun checkValidation(fromDate: String, toDate: String, time: String): Boolean {
        if (fromDate.isEmpty()){
            Toast.makeText(context, R.string.checkField, Toast.LENGTH_SHORT).show()
            return false
        }else if (toDate.isEmpty()){
            Toast.makeText(context,  R.string.checkField, Toast.LENGTH_SHORT).show()
            return false
        }else if (time.isEmpty()){
            Toast.makeText(context,  R.string.checkField, Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }

    }


}