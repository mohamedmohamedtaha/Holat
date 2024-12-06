package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Priority(
    val availability: Int, // 0
    val color: String, // #212ab0
    val created_at: String, // 2020-01-22 07:27:58
    val deleted_at: String?= null, // null
    val id: Int, // 3
    val is_default: Int, // 1
    val linked: Int, // 0
    val status: Int, // 0
    val title_ar: String, // متوسط
    val title_en: String, // medium
    val updated_at: String // 2022-05-20 19:04:43
) : Parcelable