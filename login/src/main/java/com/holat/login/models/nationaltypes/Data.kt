package com.holat.login.models.nationaltypes

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String, // 2020-01-22 07:27:58
    val default: Int, // 0
    @SerializedName("deleted_at")
    val deletedAt: String, // null
    val id: Short, // 1
    @SerializedName("title_ar")
    val titleAr: String, // رقم هوية
    @SerializedName("title_en")
    val titleEn: String, // NATIONAL ID
    @SerializedName("updated_at")
    val updatedAt: String // 2023-09-26 10:50:26
)