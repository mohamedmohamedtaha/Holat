package com.holat.holat.ui.dynamic.models


import com.google.gson.annotations.SerializedName

data class GsonDataItem(
    @SerializedName("columnName")
    var columnName: String = "",
    @SerializedName("fillOn")
    val fillOn: String = "",
    @SerializedName("getAfterSync")
    val getAfterSync: Int = 0,
    @SerializedName("gsonID")
    val gsonID: Int = 0,
    @SerializedName("processDesc")
    var processDesc: String = "",
    @SerializedName("reSync")
    val reSync: Int? = null,
    @SerializedName("reSyncCountLimit")
    val reSyncCountLimit: Int? = 0,
    @SerializedName("sendReceiveFlag")
    var sendReceiveFlag: Int? = 0,
    @SerializedName("tableIdColumn")
    val tableIdColumn: String? = null,
    @SerializedName("tableName")
    var tableName: String = "",
    @SerializedName("totalElements")
    val totalElements: Int = 0,
    @SerializedName("versionID")
    val versionID: Int? = null,
    @SerializedName("where_Condition")
    var whereCondition: String = "",
    var questionId: String = "",

    var columnNameOriginal: List<String>? = null,

){
    var columnsWithValues =  ArrayList<SaveData>()
}
