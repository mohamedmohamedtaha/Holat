package com.holat.holat.ui.dynamic.models


data class MultiCheckModel(var lookupListId:String = "", var seq:String = "", var value1:String = "", var value2:String = "", var value3:String = ""){
    var sampleId = ""
    var lookupListTxt =""
    var questId =""
    var columnName =""
    var columnNameOriginal: List<String>? = null
    var tableName =""
    var whereCondition =""
    var isCheck: Boolean = false
    var visible1: Boolean = true
    var visible2: Boolean = true
    var visible3: Boolean = true
    class DiffUtils : androidx.recyclerview.widget.DiffUtil.ItemCallback<MultiCheckModel>() {
        override fun areItemsTheSame(oldItem: MultiCheckModel, newItem: MultiCheckModel): Boolean {
            return oldItem.lookupListId == newItem.lookupListId
        }

        override fun areContentsTheSame(oldItem: MultiCheckModel, newItem: MultiCheckModel): Boolean {
            return oldItem == newItem
        }
    }
}