package com.holat.holat.utils.listener

import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.MultiCheckModel
import com.holat.holat.ui.dynamic.models.TablesModel

interface OtherListener {
    // fun other(globalView: GlobalView)
    fun other(tablesModel: TablesModel)
}

interface GlobalViewListener {
    fun globalView(globalView: GlobalView)
}

interface SpinnerListener {
    fun resetValue()
}

interface CheckBoxAdapterListener {
    fun hideAndShow(multiCheckModel: MultiCheckModel)
}

interface CheckBoxListener {
    fun checked(checked: Boolean)
}