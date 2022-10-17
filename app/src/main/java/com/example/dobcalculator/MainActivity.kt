package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

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
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpDialog=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view,selectedYear,selectedMonth,selectedDayOfMonth->
//                Toast.makeText(this,"Pressed button for date picker\n${selectedYear},${selectedMonth+1},${selectedDayOfMonth}" , Toast.LENGTH_LONG).show()
                dispDateTV?.text = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"

                val simpDF=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val dateObj=simpDF.parse("${selectedDayOfMonth}/${selectedMonth}/${selectedYear}")
                dateObj?.let {
                    val selDateInMinutes= dateObj.time/60000
                    val currentDateInMinutes= (simpDF.parse(simpDF.format(System.currentTimeMillis()))).time /60000
                    dispMinTV?.text="${(currentDateInMinutes-selDateInMinutes).toString()}"
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