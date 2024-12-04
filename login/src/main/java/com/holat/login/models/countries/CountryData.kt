package com.holat.login.models.countries

import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("country_code")
    val countryCode: String, // KSA
    @SerializedName("created_at")
    val createdAt: String, // 2020-11-09 16:38:49
    @SerializedName("deleted_at")
    val deletedAt: Any, // null
    val id: Int, // 9
    @SerializedName("is_default")
    val isDefault: Int, // 0
    val lat: String, // 00123
    val long: String, // 00332
    @SerializedName("phone_code")
    val phoneCode: String, // 966
    @SerializedName("title_ar")
    val titleAr: String, // المملكة العربية السعودية
    @SerializedName("title_en")
    val titleEn: String, // KSA
    @SerializedName("updated_at")
    val updatedAt: String, // 2024-08-11 13:50:46
    @SerializedName("user_id")
    val userId: Int // 314
)