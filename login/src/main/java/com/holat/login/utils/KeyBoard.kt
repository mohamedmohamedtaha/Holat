package com.holat.login.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
Created by Mohamed Mohamed Taha on 1/27/2024
 */
fun View.showKeyboard(){
    this.requestFocus()
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
fun View.hideKeyboard(){
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}