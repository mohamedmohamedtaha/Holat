package com.holat.login.network

import okhttp3.ResponseBody

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
sealed class NetworkResult<out T:Any> {
    data class Loading(val loading: Boolean = true):NetworkResult<Nothing>()
//    data class Success<out T: Any>(val code:Int, val data:T) :NetworkResult<T>()
    data class Success<out T: Any>(val data:T) :NetworkResult<T>()
    data class Error(val code: Int, val responseBody: ResponseBody?):NetworkResult<Nothing>()
    data class Exception(val e: Throwable):NetworkResult<Nothing>()

    data class ErrorEX(val exception: kotlin.Exception?) : NetworkResult<Nothing>()

}