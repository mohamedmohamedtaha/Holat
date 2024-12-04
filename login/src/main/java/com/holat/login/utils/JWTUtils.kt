package com.holat.login.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

object JWTUtils {
    @Throws(Exception::class)
    fun decodedJWT(JWTEncoded: String): String {
        var decodedString = ""
        try {
            val split = JWTEncoded.split(".").toTypedArray()
            decodedString = getJson(split[1])
            /*  Log.e("JWT_DECODED", "Header: " + getJson(split[0]))
              Log.e("JWT_DECODED", "Body: " + getJson(split[1]))*/
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
        return decodedString
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, Charset.defaultCharset())
    }
}