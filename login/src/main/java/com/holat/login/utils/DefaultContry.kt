package com.holat.login.utils

import android.view.View
import com.hbb20.CountryCodePicker

/**
Created by Mohamed Mohamed Taha on 2/22/2024
 */

fun View.setDefaultCountry(phoneNumberPicker: CountryCodePicker, countryCode:String){
    phoneNumberPicker.setCountryForNameCode(countryCode)

}