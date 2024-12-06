package com.holat.holat.ui.dynamic.models

import com.holat.login.models.LookUpModel


private const val checkCondition = "."

class CustomAttributes(
    var questDbColumnName: String = "",
    var questDbTableName: String = "",
    var questName: String = "",
    var questId: String = "",
    var questCode: String = "",
    var colType: String = "",
    var questToolTip: String = "",
//    var spinnerValues: ArrayList<PopupItem>? = null,
    var lookUpValues: ArrayList<LookUpModel>? = null,
    var adapterValues: ArrayList<MultiCheckModel>? = null,

    ) {
    var value = ""
        get() = field
        set(value) {
            field = if (value == checkCondition) "" else value
        }
}

