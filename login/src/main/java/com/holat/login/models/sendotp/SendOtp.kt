package com.holat.login.models.sendotp

import com.google.gson.annotations.SerializedName
import com.holat.login.models.createaccount.GlobalData

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
open class SendOtp(
    @SerializedName("captcha_code")
    val captchaCode: String,
    val key: String, val rememberMy: Boolean = false, mobile: String, nationalId: Long
) : GlobalData(mobile = mobile, nationalId = nationalId)
