package com.holat.login.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object Utils {
    private const val TAG = "utils"
    private const val characterEncoding = "UTF-8"
    private const val cipherTransformation = "AES/CBC/PKCS5Padding"
    private const val aesEncryptionAlgorithm = "AES"

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivity.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getBatteryLevel(context: Context): Float {
        val batteryIntent = context.registerReceiver(
            null, IntentFilter(
                Intent.ACTION_BATTERY_CHANGED
            )
        )
        val level = batteryIntent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        return if (level == -1 || scale == -1) {
            50.0f
        } else level.toFloat() / scale.toFloat() * 100.0f
    }

    fun playToastSound(context: Context) {
        try {
            val afd = context.assets.openFd("toast.mp3")
            val player = MediaPlayer()
            player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            player.prepare()
            player.start()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {

        try {

            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                telephonyManager.imei
            else
                telephonyManager.deviceId
        } catch (ex: Exception) {
            Log.e("getDeviceId", "getDeviceId() called with: context = $context")

        }
        return "null"
    }


    fun encrypt(plainText: String, key: String): String {
        try {
            val plainTextbytes = plainText.toByteArray(charset(characterEncoding))
            val keyBytes: ByteArray = getKeyBytes(key)
            return Base64.encodeToString(encrypt(plainTextbytes, keyBytes, keyBytes), Base64.DEFAULT).trim()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ""
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    private fun encrypt(
        plainText: ByteArray?,
        key: ByteArray?,
        initialVector: ByteArray?
    ): ByteArray? {
        val cipher = Cipher.getInstance(cipherTransformation)
        val secretKeySpec = SecretKeySpec(key, aesEncryptionAlgorithm)
        val ivParameterSpec = IvParameterSpec(initialVector)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        return cipher.doFinal(plainText)
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getKeyBytes(key: String): ByteArray {
        val keyBytes = ByteArray(16)
        val parameterKeyBytes = key.toByteArray(charset(characterEncoding))
        System.arraycopy(
            parameterKeyBytes,
            0,
            keyBytes,
            0,
            Math.min(parameterKeyBytes.size, keyBytes.size)
        )
        return keyBytes
    }

    fun getCurrentDate(): String {
        var fullDate: String? = null
        val c = Calendar.getInstance()
        val hour = String.format("%02d", c[Calendar.HOUR_OF_DAY])
        val minute = String.format("%02d", c[Calendar.MINUTE])
        val second = String.format("%02d", c[Calendar.SECOND])
        val todayDate =
            c[Calendar.YEAR] * 10000 + (c[Calendar.MONTH] + 1) * 100 + c[Calendar.DAY_OF_MONTH]
        val dateString = todayDate.toString()
        try {
            val year = String.format("%04d", dateString.substring(0, 4).toInt())
            val month = String.format("%02d", dateString.substring(4, 6).toInt())
            val day = String.format("%02d", dateString.substring(6, 8).toInt())
//            if (date) {
//                fullDate = "$day/$month/$year"
//            } else if (time) {
//                fullDate = "$hour:$minute:$second"
//            } else if (dateAndTime) {
            fullDate = "$day/$month/$year $hour:$minute:$second"
            //  }
        } catch (e: java.lang.Exception) {
            e.message
        }
        return convertArabicDigitsToEnglish(
            fullDate.toString()
        )
    }

    fun returnTwoDigitNumber(num: Int): String {
        return (if (num < 10) "0" else "") + num
    }

    fun convertArabicDigitsToEnglish(input: String): String {
        return input.replace("\u0660", "0").replace("\u0661", "1")
            .replace("\u0662", "2").replace("\u0663", "3")
            .replace("\u0664", "4").replace("\u0665", "5")
            .replace("\u0666", "6").replace("\u0667", "7")
            .replace("\u0668", "8").replace("\u0669", "9")
    }

    fun isValidUserId(id: String): Boolean {
        var res = false
        var sum_val = 0
        var value = ""
        if (id.length < 10) {
            return false
        } else {
            if (id != "7777777777" || id == "8888888888" || id == "8888888888")
                return true
            var m = 0
            for (i in 0..9) {
                m = i + 1
                if (i % 2 == 0) {
                    value = (id.substring(i, m).toInt() * 2).toString() + ""
                    sum_val = sum_val + value.substring(0, 1).toInt()
                    if (value.length > 1) {
                        sum_val = sum_val + value.substring(1, 2).toInt()
                    }
                } else {
                    sum_val = sum_val + id.substring(i, m).toInt()
                }
            }
        }
        if (sum_val % 10 == 0) {
            res = true
        }
        return res
    }

    fun getStampTimeId(): String {
        var fullDate: String? = null
        val c = Calendar.getInstance()
        val hour = String.format("%02d", c[Calendar.HOUR_OF_DAY])
        val mint = String.format("%02d", c[Calendar.MINUTE])
        val sec = String.format("%02d", c[Calendar.SECOND])
        val miliSec = String.format("%02d", c[Calendar.MILLISECOND])
        val todaysDate = (c[Calendar.YEAR] * 10000
                + (c[Calendar.MONTH] + 1) * 100
                + c[Calendar.DAY_OF_MONTH])
        val DateString = todaysDate.toString()
        try {
            val Year = String.format("%04d", DateString.substring(0, 4).toInt())
            val Month = String.format("%02d", DateString.substring(4, 6).toInt())
            val Day = String.format("%02d", DateString.substring(6, 8).toInt())
            fullDate = Day + Month + Year + hour + mint + sec + miliSec
        } catch (e: java.lang.Exception) {
            e.message
        }
        return convertArabicDigitsToEnglish(fullDate.toString())
    }

    fun getAge(year: Int, month: Int, day: Int): Int {
        Log.e(TAG, "getAge() called with: year = $year, month = $month, day = $day")
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob[year, month] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        val ageInt = age
        Log.e(TAG, "getAge() called with: ageInt = $ageInt")
        return ageInt
    }


    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun getStringValue(s: String?): String {
        return s ?: ""
    }

    fun getIntegerValue(value: String?): Int? {
        var m = 0
        try {
            m = if (value == null || value.trim { it <= ' ' }.isEmpty()
                || value.trim { it <= ' ' } == "null" || value.trim { it <= ' ' } == "0.0"
            ) 0 else {
                if (value.trim { it <= ' ' } == "-" && value.trim { it <= ' ' }.length == 1) 0 else value.toInt()
            }
        } catch (e: java.lang.Exception) {
        }
        return m
    }


    fun stringMatch(word1: String, word2: String): Boolean {
        return word1.trim() == word2
    }

     fun isNumberOrString(toCheck:String):Boolean{
       return toCheck.toIntOrNull() != null
    }
    fun isNumeric(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    fun isNumberNumeric(toCheck: String): Boolean {
        return toCheck.all { char -> char.isDigit() }
    }
}