package com.holat.holat.ui.dynamic.models

import android.text.TextWatcher
import com.holat.login.models.LookUpModel

private const val checkCondition = "."

class TablesModel {
    var questId = ""
    var questSeq = ""
    var questUiType = ""
    var partId = ""
    var partNo = ""
    var questName = ""
    var questCode = ""
    var questToolTip = ""
    var questDbColumnName = ""
    var questDbTableName = ""
    var questLookUpId = ""
    var questRefId = ""
    var readOnlyFlag = ""
    var visibleFlag = ""
    var requiredFlag = ""
    var lanID = ""
    var maxValue = ""
    var minValue = ""
    var maxLength = ""
    var minLength = ""
    var androidKey = ""
    var refValueIfNull = ""
    var calculationType = 0
    var colType: String = ""

    //    var spinnerValues: ArrayList<PopupItem>? = null
    var lookUpValues: ArrayList<LookUpModel>? = null

    //    var adapterValues: ArrayList<MultiCheckModel>? = null
    var textWatcher: TextWatcher? = null
    var value = ""
        get() = field
        set(value) {
            field = if (value == checkCondition) "" else value
        }
    var justOnce = false
}