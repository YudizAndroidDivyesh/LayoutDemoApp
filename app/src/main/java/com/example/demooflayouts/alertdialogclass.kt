package com.example.demooflayouts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import java.util.Calendar

class alertdialogclass(context: Context,onSelect : (date : String) -> Unit) : Dialog(context) {
    val calender = Calendar.getInstance()
    var date1 = ""
    var date2 = ""
    var yearUser = 0
    var monthUser = 0
    var dayUser = 0

    init {
        setContentView(R.layout.custom_dialog)
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        val fromDate = findViewById<EditText>(R.id.fromDate_et)
        val toDate = findViewById<EditText>(R.id.toDate_et)
        val selectDate = findViewById<EditText>(R.id.selectTime_Et)




        fromDate.setOnClickListener {
            val datePick = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    fromDate.setText("$year/$month/$dayOfMonth")
                    //date1 = "$year/ $month/ $dayOfMonth"
                    yearUser = year
                    monthUser = month
                    dayUser = dayOfMonth
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )

            if (toDate.text.isEmpty()) {
                datePick.datePicker.minDate = System.currentTimeMillis()
                datePick.show()
            } else {
                toDate.text.clear()
                datePick.datePicker.minDate = System.currentTimeMillis()
                calender.set(yearUser, monthUser, dayUser)
                datePick.datePicker.minDate = calender.timeInMillis
                datePick.show()

            }
        }
        toDate.setOnClickListener {

            val datePick = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    toDate.setText("$year/$month/$dayOfMonth")
                    date2 = "$year/ $month/ $dayOfMonth"
                },
                yearUser,
                monthUser,
                dayUser
            )
            calender.set(yearUser, monthUser, dayUser)
            datePick.datePicker.minDate = calender.timeInMillis
            datePick.show()
        }

        selectDate.setOnClickListener {
            val timePick = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectDate.setText("$hourOfDay : $minute")
                },
                12,
                0,
                true
            )
            timePick.show()
        }

        val submit = findViewById<Button>(R.id.submit_btn)
        submit.setOnClickListener {
            if (checkValidation(
                    fromDate.text.toString(),
                    toDate.text.toString(),
                    selectDate.text.toString()
                )
            ) {
                //       Toast.makeText(context, "$fromDate , $toDate , $selectDate", Toast.LENGTH_SHORT).show()
                val alertBuilder = AlertDialog.Builder(context)
                val dialog = alertBuilder.create()
                alertBuilder.apply {
                    setTitle("Are You Sure?")
                    setPositiveButton("Yes") { _, _ ->
                        onSelect.invoke("FromDate = ${fromDate.text} \n ToDate = ${toDate.text} \n SelectTime = ${selectDate.text}")
                        dialog.dismiss()
                        dismiss()
                    }
                    setNegativeButton("No") { dialogInterface, index ->
                        dialog.dismiss()
//                        alertdialogclass(context, tvData).show()
                    }
                    setCancelable(false)
                }.show()

            } else {
                Toast.makeText(context, R.string.checkField, Toast.LENGTH_SHORT).show()
            }

        }
        val cancel = findViewById<Button>(R.id.cancel_btn)
        cancel.setOnClickListener {
            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun checkValidation(fromDate: String, toDate: String, time: String): Boolean {
        return !(fromDate.isEmpty() || toDate.isEmpty() || time.isEmpty())
    }


}