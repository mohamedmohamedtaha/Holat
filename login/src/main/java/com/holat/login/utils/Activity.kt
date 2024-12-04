package com.holat.login.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import com.holat.login.utils.Constants.NEW_PACKAGE
import java.util.Locale

/**
Created by Mohamed Mohamed Taha on 2/3/2024
 */

fun Activity.gotToMainActivity(activity: String) {
    val main = Intent(this, Class.forName("${NEW_PACKAGE}$activity"))
    main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(main)
    finish()
}
fun <T> Activity.gotToSpecificActivity(activity: Class<T>) {
    val main = Intent(this, activity)
    main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(main)
    finish()
}
fun Activity.updateResources(context: Context,language: String) :Context{
    val locale = Locale(language)
    Locale.setDefault(locale)
    val configuration: Configuration = context.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return context.createConfigurationContext(configuration)

}
 fun Activity.updateResources(language: String) {
//    val locale = Locale(language)
//    Locale.setDefault(locale)
//    val configuration: Configuration = resources.configuration
//    configuration.setLocale(locale)
//    configuration.setLayoutDirection(locale)
//    return createConfigurationContext(configuration)
     val local = Locale(language)
     Locale.setDefault(local)
     val config = Configuration()
     config.setLocale(local)
     baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
}