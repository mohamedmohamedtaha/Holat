package com.holat.login.models.createaccount

import com.google.gson.annotations.SerializedName

/**
Created by Mohamed Mohamed Taha on 12/10/2023
 */
data class Response(val status:String,val message:String,val data:Any)
data class ResponseSendOtp(val status:String,val message:String,val data:Otp)
data class Otp(@SerializedName("otp_code") val otpCode:String)
