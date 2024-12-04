package com.holat.login.utils

import android.content.Intent
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.holat.login.LoginActivity
import com.holat.login.utils.Constants.TOKEN_EXPIRED
import com.holat.login.models.ErrorResponse
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException


fun View.onError(throwable: Exception?) {
    try {
        when (throwable) {
            is RuntimeException -> {
                when (throwable) {
                    is HttpException -> {
//                        throwable.response()?.errorBody()?.let {
//                            showSnackBar(it.string())
//                            Log.e("errore", it.toString())
//                        }
                        val gson = Gson()
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        try {
                            val errorResponse: ErrorResponse? =
                                gson.fromJson(throwable.response()?.errorBody()?.charStream(), type)
                            if (errorResponse != null) {
                                this.showSnackBar(errorResponse.message)
                                if (errorResponse.details == TOKEN_EXPIRED) {
                                    val intent = Intent(context, LoginActivity::class.java)
                                    intent.putExtra(Constants.IS_REFRESH_TOKEN, true)
                                    //or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(intent)
                                    return
                                }
                            }
                        } catch (ex: Exception) {
                            this.showSnackBar(ex.message.toString())
//                            if (ex.message == "java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $") {
//                                throwable.response()?.errorBody()?.let {
//                                    //  showSnackBar(it?.buffer.toString())
//                                    showSnackBar(it.string())
//                                    Log.e("errore", it.toString())
//                                    Log.e("errore", it.source().toString())
//                                    Log.e("errore", it.source().buffer.toString())
//                                    Log.e("errore", it.source().buffer.buffer.toString())
//                                }
//                            } else
//                                showSnackBar(ex.message.toString())

                        }
                    }

                    is IllegalArgumentException -> {
                        throwable.message?.let { this.showSnackBar(it) }
                    }
                }

            }

            is ConnectException, is SocketException -> {
                this.showSnackBar("Connect to internet")
            }

            is SocketTimeoutException -> {
                this.showSnackBar("Connection time out")
            }
//            is ServerError -> {
//                throwable.message?.let { this.showSnackBar(it) }
//            }
            //            else if (throwable is NullPointerException)
            //                this.showSnackBar(throwable.message.toString())
            else -> {
                this.showSnackBar("Something wrong here")
                throwable?.printStackTrace()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        this.showSnackBar(throwable?.message.toString())
    }
}