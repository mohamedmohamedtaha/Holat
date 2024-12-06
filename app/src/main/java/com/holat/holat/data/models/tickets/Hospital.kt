package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hospital(
    val active: Int, // 1
    val address_ar: String?= null, // null
    val address_en: String?= null, // null
    val city_id: String?= null, // null
    val code: String, // Rajhi
    val country_id: Int, // 9
    val created_at: String, // 2022-11-12 16:19:34
    val default_role_id: Int, // 155
    val default_team_tier_id: String?= null, // null
    val deleted_at: String?= null, // null
    val google_map_url: String?= null, // null
    val hotline: String?= null, // null
    val id: Int, // 9
    val mobile: String?= null, // null
    val organization_type_id: Int, // 2
    val portal: Int, // 1
    val region_id: String?= null, // null
    val responsibility: String, // associative
    val supervision_organization_id: Int, // 1
    val telephone: String?= null, // null
    val timezone: String?= null, // null
    val title_ar: String, // مصرف الراجحي
    val title_en: String, // Al Rajhi Bank
    val updated_at: String // 2022-12-31 11:07:26
) : Parcelable