package com.holat.holat.ui.dynamic.models

import android.widget.TextView

class CustomView<T>{
    var typeView:T? =null
    var title:TextView? =null
    var customAttributes: CustomAttributes?=null
    var recyclerViewData :ArrayList<MultiCheckModel>?= null
    var headerTitleForTables: ArrayList<TablesModel>?= null
}