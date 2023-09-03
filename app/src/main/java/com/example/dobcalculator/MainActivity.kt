package com.example.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var dispDateTV: TextView?=null
    private var dispMinTV: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dispDateTV=findViewById(R.id.dispDate)
        dispMinTV=findViewById(R.id.dispMin)
        val dateBtn: Button=findViewById(R.id.dateBtn)
        dateBtn.setOnClickListener {
            clickDatePicker()

        }

    }
    @SuppressLint("SetTextI18n")
    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpDialog=DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth->

                dispDateTV?.text = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"
                Toast.makeText(applicationContext,"Pressed button for date picker\nDay: $selectedDayOfMonth, Month: ${selectedMonth+1}, Year: $selectedYear" , Toast.LENGTH_LONG).show()
                val simpDF=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val dateObj=simpDF.parse("${selectedDayOfMonth}/${selectedMonth}/${selectedYear}")
                dateObj?.let {
                    val selDateInMinutes= dateObj.time/60000
                    val currentDateInMinutes= (simpDF.parse(simpDF.format(System.currentTimeMillis())))!!.time /60000
                    dispMinTV?.text= (currentDateInMinutes-selDateInMinutes).toString()
                }

          },
            year,
            month,
            day
        )

        dpDialog.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpDialog.show()
    }
}