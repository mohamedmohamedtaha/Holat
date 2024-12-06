package com.holat.holat.ui.dynamic.views.initialize

import android.app.Activity
import android.widget.TextView
import com.holat.holat.ui.dynamic.models.GlobalView

fun Activity.drawLabelOrTitle(isTitleOrLabel: Boolean, globalView: GlobalView): TextView {
    return if (isTitleOrLabel) {
        val title = drawTitle(globalView)
        title
    } else {
        val label = drawLabel(globalView)
        label
    }
}

fun Activity.drawLabelOrTitle(isTitleOrLabel: Boolean, name:String): TextView {
    return if (isTitleOrLabel) {
        val title = drawTitle(name)
        title
    } else {
        val label = drawLabel(name)
        label
    }
}

