package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubReason(
    val active: Int, // 1
    val created_at: String, // 2022-11-19 21:49:54
    val deleted_at: String?= null, // null
    val icon: String?= null, // null
    val id: Int, // 5
    val required_other: Int, // 0
    val title_ar: String, // البطاقات الائتمانية
    val title_en: String, // Credit Cards
    val updated_at: String // 2022-11-20 13:32:06
) : Parcelable