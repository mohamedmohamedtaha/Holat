package com.holat.holat.data.models.compliant.tickettype

import com.google.gson.annotations.SerializedName

data class IntegrationApi(
    @SerializedName("api_url")
    val apiUrl: String, // /api/get_violation_details
    @SerializedName("created_at")
    val createdAt: String, // 2021-10-11 17:40:34
    @SerializedName("deleted_at")
    val deletedAt: Any, // null
    val id: Int, // 5
    @SerializedName("request_body")
    val requestBody: String, // [national_id,fine_number]
    @SerializedName("title_ar")
    val titleAr: String, // المعارضات
    @SerializedName("title_en")
    val titleEn: String, // المعارضات
    @SerializedName("updated_at")
    val updatedAt: String // 2021-10-11 17:40:34
)