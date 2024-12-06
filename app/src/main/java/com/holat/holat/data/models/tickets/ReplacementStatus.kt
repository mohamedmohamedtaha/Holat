package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReplacementStatus(
    val active: Int, // 1
    val color: String, // #de3cde
    val created_at: String, // 2022-06-28 19:52:31
    val default: Int, // 0
    val deleted_at: String?= null, // null
    val display_order: Int, // 9
    val display_to_customers: Int, // 1
    val id: Int, // 8
    val main_status_id: Int, // 1
    val replacement_status_id: String?=null, // null
    val replacement_status_text: String?=null, // null
    val replacement_status_text_ar: String?=null, // null
    val show_status_text: Int, // 0
    val title_ar: String, // تحت المعالجة لدي الجهة المالية
    val title_en: String, // Under Process
    val updated_at: String, // 2023-03-07 21:08:31
    val use_replacement: Int // 0
):Parcelable