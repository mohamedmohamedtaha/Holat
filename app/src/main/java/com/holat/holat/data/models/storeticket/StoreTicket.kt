package com.holat.holat.data.models.storeticket

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 2/28/2024
 */
data class StoreTicket(
    @SerializedName("ticket_type_id") var ticketTypeId: Int,
    @SerializedName("main_reason_id") var mainReasonId: Int,
    @SerializedName("hospital_id")
    var hospitalId: Int,
    @SerializedName("sub_reason_id")
    var subReasonId: Int,
    @SerializedName("sub_sub_reason_id")
    var subSubReasonId: Int,
    @SerializedName("details")
    var details: String,
    var files: ArrayList<String> = arrayListOf())