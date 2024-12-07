package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.widget.CheckBox
import com.holat.holat.data.models.fields.Field
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.SaveData
import com.holat.holat.ui.dynamic.views.initialize.customCheckBox
import com.holat.holat.utils.listener.CheckBoxListener

class CustomCheckBox {
    private suspend fun drawCheckBox(
        field :Field,
        globalView: GlobalView,
        activity: Activity,
        checkBoxListener: CheckBoxListener,
    ): CustomView<CheckBox>? {
        globalView.checkBox = CustomView()
        globalView.checkBox?.typeView = activity.customCheckBox(
            globalView,
            checkBoxListener = checkBoxListener
        )
        return globalView.checkBox
    }

    suspend fun drawCheckBoxOnView(
        globalView: GlobalView,
        field: Field,
        activity: Activity,
        otherListener: () -> Unit,
        drawView: suspend (GlobalView) -> Unit,
    ): GlobalView {
        val customView = drawCheckBox(
            field = field,
            globalView = globalView,
            activity = activity,
            checkBoxListener = object : CheckBoxListener {
                override fun checked(checked: Boolean) {
                    otherListener()
                }
            })
        globalView.field = field
        globalView.checkBox = customView
        drawView(globalView)
        return globalView
    }
}