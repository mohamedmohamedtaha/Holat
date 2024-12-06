package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.widget.Button
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.views.initialize.drawButton
import com.holat.holat.ui.dynamic.views.initialize.drawLabelOrTitle
import com.holat.holat.utils.listener.GlobalViewListener

class CustomButton {
    private fun drawButton(
        globalView: GlobalView,
        activity: Activity,
        clickButton: GlobalViewListener,
        noTitle: Boolean,
    ): CustomView<Button>? {
        globalView.buttonView = CustomView()
        if (!noTitle)
            globalView.buttonView?.title = activity.drawLabelOrTitle(true, globalView)
        globalView.buttonView?.typeView = activity.drawButton(
            clickButton = clickButton
        )

        return globalView.buttonView
    }

    suspend fun drawButtonOnView(
        activity: Activity,
        globalView: GlobalView,
        noTitle: Boolean = false,
        globalViewListener: (GlobalView) -> Unit,
        drawView: suspend (GlobalView) -> Unit,
    ): GlobalView {
        val customButton = drawButton(
            globalView = globalView,
            activity = activity,
            noTitle = noTitle,
            clickButton = object : GlobalViewListener {
                override fun globalView(globalView: GlobalView) {
                    globalViewListener(globalView)
                }
            })
        globalView.buttonView = customButton
        if (!noTitle)
            globalView.buttonView?.title = customButton?.title
        drawView(globalView)
        return globalView
    }
}

