package com.holat.login.models.login

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
data class NationalIdType(
    val id: Long,
    @SerializedName("title_en") val titleEn: String,
    @SerializedName("title_ar") val titleAr: String,
    @SerializedName("deleted_at") val deletedAt: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at")val updatedAt:String,
    val default:Short

)
