package com.holat.holat.data.models.compliant.mainreasons

import com.google.gson.annotations.SerializedName
import com.holat.login.utils.Constants
import com.yariksoffice.lingver.Lingver

data class MainReasons(
    val active: Int, // 1
    val id: Int, // 25
    @SerializedName("title_ar")
    val titleAr: String, // زراعه
    @SerializedName("title_en")
    val titleEn: String, // Plant
) {

    val color: String? = null // #e0e0e0

    @SerializedName("created_at")
    val createdAt: String? = null // 2020-12-01 00:01:45

    @SerializedName("deleted_at")
    val deletedAt: String? = null // null
    val icon: String? = null // null

    @SerializedName("required_other")
    val requiredOther: Int? = null // 1

    @SerializedName("updated_at")
    val updatedAt: String? = null// 2023-11-26 16:18:32
    override fun toString(): String {
        val currentLanguage = Lingver.getInstance().getLanguage()
        return if (currentLanguage == Constants.AR_LANGUAGE)
            titleAr
        else
            titleEn
    }
}