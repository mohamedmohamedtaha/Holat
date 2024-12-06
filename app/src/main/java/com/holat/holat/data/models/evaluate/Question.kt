package com.holat.holat.data.models.evaluate

data class Question(
    val active: Int, // 1
    val closed_endeds: List<ClosedEnded>,
    val created_at: String, // 2024-02-13 12:03:15
    val deleted_at: Any, // null
    val details_required: Int, // 0
    val id: Int, // 96
    val question_category_id: Int, // 4
    val self_service: Int, // 1
    val title_ar: String, // ما مدى رضاك عن الخدمة المقدمة عبر نظام ساما تهتم
    val title_en: String, // ?How satisfied are you with SAMACares services
    val type: String, // rate
    val updated_at: String // 2024-02-13 12:03:15
)