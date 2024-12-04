package com.holat.login.models.login

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
data class ResponseLogin(
    val token: String,
    @SerializedName("IdentityServerToken") val identityServerToken: String,
    @SerializedName("national_id_type")val nationalIdType:NationalIdType,
    @SerializedName("data")val login: Login
)
