package com.holat.login.utils

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import com.holat.login.R
import com.holat.login.utils.listener.ClickListener
import java.util.Calendar

/**
Created by Mohamed Mohamed Taha on 12/11/2023
 */
object DatePickerDialogUtil {
    fun selectDateOfBirthGregorian(context: Context, editText: EditText, title: String) {
        val calender = Calendar.getInstance()
        val dateOfBirthClickListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val myCalender = Calendar.getInstance()
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, month)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                editText.setText(
                    Formatter.format(
                        myCalender.time.toString(),
                        Formatter.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY,
                        Formatter.DD_MM_YYYY
                    )
                )
            }
        val b = DatePickerDialog(
            context, R.style.CustomDatePickerDialog,
            dateOfBirthClickListener,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        b.setTitle(title)
        b.show()
    }

    fun selectDateOfBirthGregorian(
        context: Context,
        editText: EditText,
        callback: ClickListener<CalenderModel>
    ) {
        val calender = Calendar.getInstance()
        val dateOfBirthClickListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val myCalender = Calendar.getInstance()
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, month)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val year = myCalender.get(Calendar.YEAR)
                val month = myCalender.get(Calendar.MONTH) + 1
                val day = myCalender.get(Calendar.DAY_OF_MONTH)
                val item = CalenderModel(year.toString(), month.toString(), day.toString())
                callback.onClick(editText.rootView, item)
                Log.e(
                    "rrrrrr",
                    myCalender.get(Calendar.YEAR).toString() + " " + myCalender.get(Calendar.MONTH)
                        .toString()
                )
                editText.setText(
                    Formatter.format(
                        myCalender.time.toString(),
                        Formatter.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY,
                        Formatter.DD_MM_YYYY
                    )
                )
            }
        val b = DatePickerDialog(
            context, R.style.CustomDatePickerDialog,
            dateOfBirthClickListener,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        b.setTitle(context.getString(R.string.date_of_birth))
        b.show()
    }

    fun View.selectDateOfBirthGregorian(
        editText: EditText,
        title: String,
        enableFutureDate: Boolean = true
    ) {
        val calender = Calendar.getInstance()
        val dateOfBirthClickListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val myCalender = Calendar.getInstance()
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, month)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val year = myCalender.get(Calendar.YEAR)
                val month = myCalender.get(Calendar.MONTH) + 1
                val day = myCalender.get(Calendar.DAY_OF_MONTH)
                val item = CalenderModel(year.toString(), month.toString(), day.toString())
                Log.e(
                    "rrrrrr",
                    myCalender.get(Calendar.YEAR).toString() + " " + myCalender.get(Calendar.MONTH)
                        .toString()
                )
                editText.setText(
                    Formatter.format(
                        myCalender.time.toString(),
                        Formatter.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY,
                        Formatter.YYYY_MM_DD
                    )
                )
            }
        val b = DatePickerDialog(
            context, R.style.CustomDatePickerDialog,
            dateOfBirthClickListener,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        //For disable the future date
        if (enableFutureDate)
            b.datePicker.maxDate = System.currentTimeMillis()

        b.setTitle(title)
        b.show()
    }
}

class CalenderModel(val year: String, val month: String, val day: String)

