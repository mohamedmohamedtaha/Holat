package com.holat.login.network

import android.annotation.SuppressLint
import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

/**
Created by Mohamed Mohamed Taha on 07/11/2023
 */
interface ApiHandler {
    @SuppressLint("SuspiciousIndentation")
    suspend fun <T : Any> handleApi(execute: suspend () -> T): NetworkResult<T> {
        return try {
            val response = execute.invoke()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        400 -> {
                            Log.e("", "Bad Request")
                            NetworkResult.Error(e.code(),e.response()?.errorBody())
                        }

                        403 -> {
                            Log.e("", "Forbidden")
                            NetworkResult.Error(e.code(),e.response()?.errorBody())
                        }

                        404 -> {
                            Log.e("", "Page not found")
                            NetworkResult.Error(e.code(),e.response()?.errorBody())
                        }

                        401 -> {
                            Log.e("", "Unauthenticated Error")
                            NetworkResult.Error(e.code(),e.response()?.errorBody())
                        }

                        502 -> {
                            Log.e("", "Bad Gateway")

                        }

                        else -> {
                            Log.e("", "Log error ${e.code()}")

                        }
                    }
                    return NetworkResult.Error(e.code(),e.response()?.errorBody())
                }

                is ConnectException -> {
                    NetworkResult.ErrorEX(e)
                }

                is UnknownHostException -> {
                    NetworkResult.ErrorEX(e)
                }

                is CancellationException -> {
                    NetworkResult.ErrorEX(e)
                }

                is IOException -> {
                    NetworkResult.ErrorEX(e)
                }

                else -> {
                    return NetworkResult.ErrorEX(e)
                }
            }
            return NetworkResult.ErrorEX(e)
        }
    }


    suspend fun <T : Any> responseAPI(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return try {
            val response = apiCall.invoke()
            NetworkResult.Success(response)
        } catch (e: Exception) {
            return NetworkResult.ErrorEX(e)
        }
    }
//    suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): NetworkResult<T> {
//        return try {
//            val response = execute()
//            if (response.isSuccessful)
//                NetworkResult.Success(response.code(), response.body()!!)
//             else
//                NetworkResult.Error(response.code(), response.errorBody()?.string())
//        } catch (e: HttpException) {
//            NetworkResult.Error(e.code(), e.message())
//        } catch (e: Throwable) {
//            NetworkResult.Exception(e)
//        }
//    }
}