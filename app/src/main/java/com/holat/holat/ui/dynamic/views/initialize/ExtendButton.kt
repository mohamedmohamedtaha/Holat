package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import com.holat.holat.utils.listener.GlobalViewListener
import com.google.android.material.button.MaterialButton

/**
Created by Mohamed Mohamed Taha on 10/31/2024
 */

fun Activity.drawButton(
    clickButton: GlobalViewListener,
): MaterialButton {
    val layoutParams = initializeLinearLayoutCompatMatchParent()
    val button = MaterialButton(this)
   // button.text = this.getString(R.string.add)
    button.setOnClickListener {
//        clickButton.other(customAttributes)
    }
    button.layoutParams = layoutParams
    return button
}