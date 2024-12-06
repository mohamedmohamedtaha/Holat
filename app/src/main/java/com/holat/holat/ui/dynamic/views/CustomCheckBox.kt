package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.widget.CheckBox
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.SaveData
import com.holat.holat.ui.dynamic.views.initialize.customCheckBox
import com.holat.holat.utils.listener.CheckBoxListener

class CustomCheckBox {
    private suspend fun drawCheckBox(
        allAnswersFirstTable: ArrayList<SaveData>,
        globalView: GlobalView,
        activity: Activity,
        checkBoxListener: CheckBoxListener,
    ): CustomView<CheckBox>? {
        allAnswersFirstTable.forEach {
            if (it.columnName.lowercase() == globalView.tablesModel.questDbColumnName.lowercase())
                globalView.tablesModel.value = it.value
        }
        globalView.checkBox = CustomView()
        globalView.checkBox?.typeView = activity.customCheckBox(
            globalView.tablesModel,
            checkBoxListener = checkBoxListener
        )
        return globalView.checkBox
    }

    suspend fun drawCheckBoxOnView(
        globalView: GlobalView,
        allAnswersFirstTable: ArrayList<SaveData>,
        activity: Activity,
        otherListener: () -> Unit,
        drawView: suspend (GlobalView) -> Unit,
    ): GlobalView {
        val customView = drawCheckBox(
            allAnswersFirstTable = allAnswersFirstTable,
            globalView = globalView,
            activity = activity,
            checkBoxListener = object : CheckBoxListener {
                override fun checked(checked: Boolean) {
                    otherListener()
                }
            })
        globalView.checkBox = customView
        drawView(globalView)
        return globalView
    }
}