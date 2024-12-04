package com.holat.login.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LookUpModel(var lookUpListId: Int, var listName: String = "", var position :Int= 0) :
    Parcelable {
    var isCheck: Boolean = false

    override fun toString(): String {
        return listName
    }
}