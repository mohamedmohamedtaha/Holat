package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketType(
    val active: Int, // 1
    val created_at: String, // 2022-11-19 21:10:14
    val default: Int, // 1
    val deleted_at: String?= null, // null
    val has_integration: Int?=null, // 0
    val icon: String?= null, // null
    val id: Int, // 1
    val integration_api_id: String?= null, // null
    val show_ports: Int, // 0
    val title_ar: String, // شكوى
    val title_en: String, // complaint
    val updated_at: String // 2022-12-05 13:09:48
) : Parcelable