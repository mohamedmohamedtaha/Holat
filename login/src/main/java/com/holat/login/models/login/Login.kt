package com.holat.login.models.login

import com.google.gson.annotations.SerializedName
import com.holat.login.models.createaccount.CreateAccount

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
class Login(
    val id: Long,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_type_id") val clientTypeId: String, birthDate: String,
    email: String, name: String, nationalIdTypeId: Short, key: String,
    mobile: String, nationalId: Long, captchaCode: String, rememberMy: Boolean
) : CreateAccount(
    birthDate = birthDate,
    email = email,
    name = name,
    nationalIdTypeId = nationalIdTypeId,
    captchaCode = captchaCode,
    key = key,
    mobile = mobile,
    nationalId = nationalId, rememberMy = rememberMy
)
