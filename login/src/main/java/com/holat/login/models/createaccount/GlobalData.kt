package com.holat.login.models.createaccount

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
open class GlobalData(
    val mobile: String,
    @SerializedName("national_id")
    val nationalId: Long
)