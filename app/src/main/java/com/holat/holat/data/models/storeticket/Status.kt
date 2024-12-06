package com.holat.holat.data.models.storeticket

data class Status(
    val active: Int, // 1
    val color: String, // #0aab4d
    val created_at: String, // 2022-06-28 19:47:32
    val default: Int, // 1
    val deleted_at: Any, // null
    val display_order: Int, // 1
    val display_to_customers: Int, // 0
    val id: Int, // 1
    val main_status_id: Int, // 1
    val replacement_status: ReplacementStatus,
    val replacement_status_id: Int, // 8
    val replacement_status_text: Any, // null
    val replacement_status_text_ar: Any, // null
    val show_status_text: Int, // 0
    val title_ar: String, // شكوي جديدة
    val title_en: String, // New Case
    val updated_at: String, // 2023-03-07 21:04:29
    val use_replacement: Int // 1
)