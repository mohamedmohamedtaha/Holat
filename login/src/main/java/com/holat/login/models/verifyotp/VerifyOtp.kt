package com.holat.login.models.verifyotp

import com.google.gson.annotations.SerializedName
import com.holat.login.models.createaccount.GlobalData

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
open class VerifyOtp(
//    @SerializedName("birthdate")
//    val birthDate: String,
    @SerializedName("otp_code") val otpCode: String,
    val rememberMe: Boolean, mobile: String, nationalId: Long
) : GlobalData(mobile = mobile, nationalId = nationalId)
