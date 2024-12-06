package com.holat.holat.data.models.tickets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClientHospital(
    val client_id: Int, // 415270
    val created_at: String, // 2023-08-24 12:40:38
    val creation_date: String?= null, // null
    val deleted_at: String?= null, // null
    val expiration_date: String?= null, // null
    val file_number: Int, // 1692870038
    val hospital_id: Int, // 1
    val id: Int, // 415323
    val updated_at: String // 2023-08-24 12:40:38
) : Parcelable