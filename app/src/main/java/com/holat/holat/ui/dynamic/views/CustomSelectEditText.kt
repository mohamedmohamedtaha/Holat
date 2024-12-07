package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.widget.EditText
import com.holat.holat.data.models.fields.Field
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.views.initialize.drawLabelOrTitle
import com.holat.holat.ui.dynamic.views.initialize.initializeEditText
import com.holat.holat.utils.listener.GlobalViewListener
import com.holat.login.utils.changeLanguage

/**
Created by Mohamed Mohamed Taha on 11/4/2024
 */
private fun Activity.customEditTextSelect(
    globalView: GlobalView,
    globalViewListener: GlobalViewListener,
): EditText {
    val editText = initializeEditText()
    editText.setOnClickListener {
        globalViewListener.globalView(globalView)
    }
    return editText
}

class CustomSelectEditText {
    private fun customSelectEditText(
        field: Field,
        activity: Activity,
        globalView: GlobalView,
        globalViewListener: GlobalViewListener
    ): CustomView<EditText>? {
        globalView.editTextView = CustomView()
        globalView.editTextView?.title = activity.drawLabelOrTitle(true, changeLanguage(field.display_name_ar,field.display_name_en))
        globalView.editTextView?.typeView = activity.customEditTextSelect(
            globalView = globalView,
            globalViewListener = globalViewListener
        )
        return globalView.editTextView
    }

    suspend fun drawSelectEditTextOnView(
        activity: Activity,
        field: Field,
        globalView: GlobalView,
        globalViewListener: (GlobalView) -> Unit,
        drawView: suspend (GlobalView) -> Unit
    ): GlobalView {
        val customView = customSelectEditText(
            field = field,
            activity = activity,
            globalView = globalView,
            globalViewListener = object : GlobalViewListener {
                override fun globalView(globalView: GlobalView) {
                    globalViewListener(globalView)
                }
            })
        globalView.field = field
        globalView.editTextView = customView
        globalView.editTextView?.title = customView?.title
        drawView(globalView)
        return globalView

    }
}