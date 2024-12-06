package com.holat.holat.data.models.organization

import com.google.gson.annotations.SerializedName

data class Organization(
    val active: Int, // 1
    @SerializedName("address_ar")
    val addressAr: Any, // null
    @SerializedName("address_en")
    val addressEn: Any, // null
    @SerializedName("city_id")
    val cityId: Int, // 8
    val code: String, // GIB
    @SerializedName("country_id")
    val countryId: Int, // 9
    @SerializedName("created_at")
    val createdAt: String, // 2022-11-12 16:15:16
    @SerializedName("default_role_id")
    val defaultRoleId: Int, // 152
    @SerializedName("default_team_tier_id")
    val defaultTeamTierId: Int, // 1
    @SerializedName("deleted_at")
    val deletedAt: Any, // null
    @SerializedName("google_map_url")
    val googleMapUrl: Any, // null
    val hotline: Any, // null
    val id: Int, // 6
    val mobile: Any, // null
    @SerializedName("organization_type_id")
    val organizationTypeId: Int, // 2
    val portal: Int, // 1
    @SerializedName("region_id")
    val regionId: Int, // 15
    val responsibility: String, // associative
    @SerializedName("supervision_organization_id")
    val supervisionOrganizationId: Int, // 1
    val telephone: Any, // null
    val timezone: Any, // null
    @SerializedName("title_ar")
    val titleAr: String, // بنك الخليج الدولي
    @SerializedName("title_en")
    val titleEn: String, // Gulf International Bank(GIB)
    @SerializedName("updated_at")
    val updatedAt: String // 2023-07-08 20:22:21
)