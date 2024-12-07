package com.holat.login.utils

import com.yariksoffice.lingver.Lingver

/**
Created by Mohamed Mohamed Taha on 12/7/2024
 */
fun changeLanguage(arabic:String,english:String):String{
    val currentLanguage = Lingver.getInstance().getLanguage()
    val displayName =
        if (currentLanguage == Constants.AR_LANGUAGE) arabic else english
    return displayName
}