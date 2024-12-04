package com.holat.login.models.cities

import com.google.gson.annotations.SerializedName

data class CityData(
    val active: Int, // 1
    @SerializedName("country_id")
    val countryId: Int, // 9
    @SerializedName("region_id")
    val regionId: Int, // 15
    @SerializedName("created_at")
    val createdAt: String, // 2020-11-09 16:39:24
    @SerializedName("deleted_at")
    val deletedAt: Any, // null
    val id: Int, // 15
    @SerializedName("title_ar")
    val titleAr: String, // الرياض
    @SerializedName("title_en")
    val titleEn: String, // الرياض
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-03-14 11:08:42
    @SerializedName("user_id")
    val userId: Int // 98
)