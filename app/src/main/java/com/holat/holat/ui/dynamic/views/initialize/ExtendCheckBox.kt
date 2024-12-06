package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import android.graphics.Typeface
import android.widget.CheckBox
import com.holat.holat.ui.dynamic.models.TablesModel
import com.holat.holat.utils.listener.CheckBoxListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
Created by Mohamed Mohamed Taha on 10/31/2024
 */
suspend fun Activity.customCheckBox(
    tablesModel: TablesModel,
    checkBoxListener: CheckBoxListener,
): CheckBox {
    val layoutParams = initializeCheckBox()
    val checkBox = CheckBox(this)
    checkBox.text = tablesModel.questName.trim()
    checkBox.setTypeface(null, Typeface.BOLD)
    withContext(Dispatchers.Main) {
        checkBox.isChecked = tablesModel.value == "1"

    }
    checkBox.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            tablesModel.value = "1"
        } else {
            tablesModel.value = ""
        }
        checkBoxListener.checked(isChecked)
    }
    checkBox.layoutParams = layoutParams
    return checkBox
}