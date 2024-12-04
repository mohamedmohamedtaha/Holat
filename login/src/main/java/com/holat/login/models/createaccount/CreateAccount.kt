package com.holat.login.models.createaccount

import com.google.gson.annotations.SerializedName
import com.holat.login.models.sendotp.SendOtp

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
open class CreateAccount(
    @SerializedName("birthdate")
    val birthDate: String,
    val email: String,
    val name: String,
    @SerializedName("national_id_type_id")
    val nationalIdTypeId: Short,
    captchaCode: String,
    key: String,
    rememberMy: Boolean,
    mobile: String,
    nationalId: Long
) : SendOtp(captchaCode, key, rememberMy, mobile, nationalId)
