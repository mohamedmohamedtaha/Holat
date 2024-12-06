package com.holat.holat.data.models.compliant.tickettype

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 2/24/2024
 */
data class TicketType(
    val active: Int, // 1
    @SerializedName("created_at")
    val createdAt: String, // 2020-11-09 16:52:58
    val default: Int, // 1
    @SerializedName("deleted_at")
    val deletedAt: String, // null
    @SerializedName("has_integration")
    val hasIntegration: Int, // 0
    val icon: String, // /storage/icons/20220613111282583.png
    val id: Int, // 1
    @SerializedName("integration_api")
    val integrationApi: IntegrationApi,
    @SerializedName("integration_api_id")
    val integrationApiId: Int, // 5
    @SerializedName("show_ports")
    val showPorts: Int, // 0
    @SerializedName("title_ar")
    val titleAr: String, // شكوى
    @SerializedName("title_en")
    val titleEn: String, // Complaint
    @SerializedName("updated_at")
    val updatedAt: String // 2023-10-17 10:29:21
)