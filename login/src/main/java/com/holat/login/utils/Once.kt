package com.holat.login.utils

import kotlinx.coroutines.flow.MutableStateFlow

/**
Created by Mohamed Mohamed Taha on 3/4/2024
 */
fun<T>MutableStateFlow<T?>.set(value:T,default:T?=null){
    this.value = value
    this.value = default
}