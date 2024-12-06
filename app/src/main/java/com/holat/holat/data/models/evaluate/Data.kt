package com.holat.holat.data.models.evaluate

data class Data(
    val active: Int, // 1
    val created_at: String, // 2020-01-30 11:04:59
    val deleted_at: Any, // null
    val description_ar: Any, // null
    val description_en: Any, // null
    val id: Int, // 4
    val questionnaire_id: Int, // 1
    val questions: List<Question>,
    val title_ar: String, // التقييم العام
    val title_en: String, // overall assessment
    val updated_at: String // 2020-01-30 11:04:59
)