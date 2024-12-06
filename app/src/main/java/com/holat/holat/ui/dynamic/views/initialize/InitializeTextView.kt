package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import android.graphics.Color
import android.widget.TextView

/**
Created by Mohamed Mohamed Taha on 3/12/2024
 */
fun Activity.initializeTextView(): TextView {
    val textView = TextView(this)
    textView.layoutParams = initializeLinearLayoutCompat()
    textView.setTextColor(Color.BLACK)
    //textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    return textView
}