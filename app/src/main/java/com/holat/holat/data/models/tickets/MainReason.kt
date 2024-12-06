package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainReason(
    val active: Int, // 1
    val color: String?= null, // null
    val created_at: String, // 2022-11-19 21:13:39
    val deleted_at: String?= null, // null
    val icon: String?= null, // null
    val id: Int, // 1
    val required_other: Int, // 0
    val title_ar: String, // البنوك
    val title_en: String, // Banking
    val updated_at: String // 2022-11-19 21:32:20
) : Parcelable