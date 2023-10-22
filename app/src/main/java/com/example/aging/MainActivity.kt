package com.example.aging

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dob = findViewById<Button>(R.id.btnDatePicker)

        dob.setOnClickListener {view->clickDatePicker(view)}
    }

    fun clickDatePicker(view:View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get((Calendar.DAY_OF_MONTH))


        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(
                this,
                "The choosen year is $selectedYear month is ${selectedMonth + 1} and day is $selectedDayOfMonth",
                Toast.LENGTH_LONG
            ).show()


            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            val selDate = findViewById<TextView>(R.id.tvSelectedDate)
            selDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinute = theDate!!.time / 60000
            val currentDate =sdf.parse((sdf.format(System.currentTimeMillis())))
            val currentDateToMinute = currentDate!!.time /60000
            val differenceInMinutes = currentDateToMinute - selectedDateInMinute
            val selDteMin = findViewById<TextView>(R.id.tvSelectedDateInMin)
            selDteMin.setText(differenceInMinutes.toString())
        }
            ,year
            ,month
            ,day).show()

    }
}