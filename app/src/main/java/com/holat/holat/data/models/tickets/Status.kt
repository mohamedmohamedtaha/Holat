package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(
    val active: Int, // 1
    val color: String, // #1e7052
    val created_at: String, // 2022-06-28 19:48:58
    val default: Int, // 0
    val deleted_at: String?= null, // null
    val display_order: Int, // 4
    val display_to_customers: Int, // 1
    val id: Int, // 4
    val main_status_id: Int, // 4
    val replacement_status: ReplacementStatus?=null, // null
    val replacement_status_id: String?= null, // null
    val replacement_status_text: String?= null, // null
    val replacement_status_text_ar: String?= null, // null
    val show_status_text: Int, // 0
    val title_ar: String, // مغلقة
    val title_en: String, // Closed
    val updated_at: String, // 2022-12-21 17:17:21
    val use_replacement: Int // 0
) : Parcelable