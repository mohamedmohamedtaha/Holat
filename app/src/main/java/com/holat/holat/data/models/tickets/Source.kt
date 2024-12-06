package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val active: Int, // 1
    val color: String, // #62b2ef
    val created_at: String, // 2022-06-28 19:47:07
    val deleted_at: String?= null, // null
    val display_order: Int, // 1
    val id: Int, // 4
    val is_default: Int, // 1
    val title_ar: String, // بوابة ساما نهتم
    val title_en: String, // SAMACares
    val updated_at: String // 2023-07-09 10:08:15
) : Parcelable