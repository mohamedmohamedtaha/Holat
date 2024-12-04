package com.holat.login.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.holat.login.R

/**
Created by Mohamed Mohamed Taha on 1/24/2024
 */
class GeneralTextWatcher(
    val view: View,
    private val editText1: AppCompatEditText,
    private val editText2: AppCompatEditText,
    private val editText3: AppCompatEditText,
    private val editText4: AppCompatEditText,
    private val editText5: AppCompatEditText,
    private val editText6: AppCompatEditText
) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        val text = p0.toString()
        when (view.id) {
            R.id.etNumber1 -> {
                if (text.length == 1)
                    editText2.requestFocus()
            }

            R.id.etNumber2 -> {
                if (text.length == 1)
                    editText3.requestFocus()
                else if (text.isEmpty())
                    editText1.requestFocus()

            }

            R.id.etNumber3 -> {
                if (text.length == 1)
                    editText4.requestFocus()
                else if (text.isEmpty())
                    editText2.requestFocus()

            }

            R.id.etNumber4 -> {
                if (text.length == 1)
                    editText5.requestFocus()
                else if (text.isEmpty())
                    editText3.requestFocus()

            }

            R.id.etNumber5 -> {
                if (text.length == 1)
                    editText6.requestFocus()
                else if (text.isEmpty())
                    editText4.requestFocus()

            }

            R.id.etNumber6 -> {
                if (text.isEmpty())
                    editText5.requestFocus()
            }
        }

    }

}