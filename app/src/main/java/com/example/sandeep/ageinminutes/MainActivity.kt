package com.example.sandeep.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.Console
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var button:Button?=null
    private var dob:TextView?=null
    private var minute:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById(R.id.button)
        dob =findViewById(R.id.dob)
        minute =findViewById(R.id.minute)

        button?.setOnClickListener{
            getDateBirth()
        }

    }

    private fun getDateBirth() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar[Calendar.YEAR]
        val month = myCalendar[Calendar.MONTH]
        val day = myCalendar[Calendar.DAY_OF_MONTH]

        val dpd=DatePickerDialog(this,{_,selectedYear,selectedMonth,selectedDay->

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            dob?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedMinute = theDate.time/60000
                val curentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                curentDate?.let {
                    val curentMinute = curentDate.time/60000

                    val differentDate = curentMinute - selectedMinute

                    minute?.text=differentDate.toString()
                }

            }

        },year,
            month,
            day
        )

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}