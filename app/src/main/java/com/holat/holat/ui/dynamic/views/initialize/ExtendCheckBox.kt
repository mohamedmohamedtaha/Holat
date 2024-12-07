package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import android.graphics.Typeface
import android.widget.CheckBox
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.TablesModel
import com.holat.holat.utils.listener.CheckBoxListener
import com.holat.login.utils.changeLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
Created by Mohamed Mohamed Taha on 10/31/2024
 */
suspend fun Activity.customCheckBox(
    globalView: GlobalView,
    checkBoxListener: CheckBoxListener,
): CheckBox {
    val layoutParams = initializeCheckBox()
    val checkBox = CheckBox(this)
    checkBox.text = changeLanguage(globalView.field?.display_name_ar.toString(),globalView.field?.display_name_en.toString())
    checkBox.setTypeface(null, Typeface.BOLD)
    withContext(Dispatchers.Main) {
        checkBox.isChecked = globalView.tablesModel.value == "1"

    }
    checkBox.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            globalView.tablesModel.value = "1"
        } else {
            globalView.tablesModel.value = "0"
        }
        checkBoxListener.checked(isChecked)
    }
    checkBox.layoutParams = layoutParams
    return checkBox
}