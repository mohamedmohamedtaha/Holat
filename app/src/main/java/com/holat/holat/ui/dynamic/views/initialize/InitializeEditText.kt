package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import android.view.ContextThemeWrapper
import android.widget.EditText
import com.holat.holat.R

/**
Created by Mohamed Mohamed Taha on 3/12/2024
 */
fun Activity.initializeEditText(): EditText {
    val editText = EditText(
        ContextThemeWrapper(this, R.style.et_choose_new_question),
        null,
        0
    )
    editText.layoutParams = initializeLinearLayoutCompatMatchParent()
    return editText
}